package com.ole.simulator.generator;

import com.ole.simulator.data.MessageData;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class IFPSRPRTGenerator {

    private final Configuration configuration;

    public IFPSRPRTGenerator(Configuration configuration) {
        this.configuration = configuration;
    }

    public MessageData generate(MessageData request) throws Exception {

        Template template = configuration.getTemplate("ifpsrprt.xml.ftl");

        Map<String, Object> model = new HashMap<>();
        var data =  new DataGenerator();
        model.put("msgId", data.getMsgId());
        model.put("time",data.getTime());
        model.put("timestamp",data.getTimestamp());
        model.put("origTxnId", request.transactionId());
        model.put("origE2EID",request.e2eReference());
        model.put("accpTime",request.initTimestamp());
        model.put("debtorAgent", request.debtorAgent());
        model.put("origMsgId",request.messageId());

        StringWriter writer = new StringWriter();
        template.process(model, writer);

        return new MessageData(data.getMsgId(), writer.toString());
    }
}
