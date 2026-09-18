package com.ole.simulator.controller;

import com.ole.simulator.generator.ControlGenerator;
import org.apache.camel.FluentProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@RestController
public class CentrolinkController {

    Logger logger = LoggerFactory.getLogger(CentrolinkController.class);
    private final ControlGenerator controlGenerator;
    private final FluentProducerTemplate producerTemplate;

    public CentrolinkController(ControlGenerator controlGenerator, FluentProducerTemplate producerTemplate) {
        this.controlGenerator = controlGenerator;
        this.producerTemplate = producerTemplate;
    }

    @PostMapping("/api/v1/request")
    public ResponseEntity<String> postMessage(@RequestHeader("X-Request-Id") String requestId, @RequestBody String request) throws Exception {

        logger.info("Request {} received at {}", requestId, Instant.now().truncatedTo(ChronoUnit.MILLIS).toString());


        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document dDoc = builder.parse(new InputSource(new StringReader(request)));


        XPath xPath = XPathFactory.newInstance().newXPath();
        Node node = (Node) xPath.evaluate("/EDoc/Msg/Header/MsgId", dDoc, XPathConstants.NODE);


        producerTemplate.withBody(dDoc).to("seda:a").send();
        return ResponseEntity.ok().header("content-type","text/xml").body(controlGenerator.generate(node.getTextContent()));
    }
}
