package com.ole.simulator.generator;

import com.ole.simulator.data.MessageData;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class ControlGenerator {

    private final Configuration configuration;

    public ControlGenerator(Configuration configuration) {
        this.configuration = configuration;
    }



    public MessageData generate(MessageData request) throws Exception {

        Template template =
                configuration.getTemplate("contrl.xml.ftl");

        var data = new DataGenerator();

        Map<String, Object> model = new HashMap<>();
        model.put("msgId", data.getMsgId());
        model.put("time",data.getTime());
        model.put("origId", request.documentId());


        StringWriter writer = new StringWriter();
        template.process(model, writer);

        return new MessageData(data.getMsgId(), writer.toString());
    }


}
