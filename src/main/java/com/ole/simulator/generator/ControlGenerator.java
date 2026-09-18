package com.ole.simulator.generator;

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



    public String generate(String msgId) throws Exception {

        Template template =
                configuration.getTemplate("contrl.xml.ftl");

        var data = new DataGenerator();

        Map<String, Object> model = new HashMap<>();
        model.put("msgId", data.getMsgId());
        model.put("time",data.getDate());
        model.put("origId", msgId);


        StringWriter writer = new StringWriter();
        template.process(model, writer);

        return writer.toString();
    }


}
