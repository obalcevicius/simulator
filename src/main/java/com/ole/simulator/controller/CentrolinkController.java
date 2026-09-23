package com.ole.simulator.controller;

import com.ole.simulator.data.MessageData;
import com.ole.simulator.generator.ControlGenerator;
import com.ole.simulator.parser.RequestParser;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.camel.FluentProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
public class CentrolinkController {

    final Logger logger = LoggerFactory.getLogger(CentrolinkController.class);
    private final ControlGenerator controlGenerator;
    private final FluentProducerTemplate producerTemplate;

    public CentrolinkController(ControlGenerator controlGenerator, FluentProducerTemplate producerTemplate) {
        this.controlGenerator = controlGenerator;
        this.producerTemplate = producerTemplate;
    }

    @PostMapping("/api/v1/request")
    public ResponseEntity<String> postMessage(HttpServletRequest request) throws Exception {
        String requestId = request.getHeader("X-Request-Id");

        logger.info("Received {}", requestId);

        MessageData requestData = RequestParser.parseRequestMessage(request.getInputStream());
        producerTemplate.withExchangeProperties(Map.ofEntries(Map.entry("internalTimestamp", requestData.internalTimestamp()), Map.entry("documentID",requestData.documentId()))).withBody(requestData).to("seda:a").send();
        return ResponseEntity.ok().header("content-type","text/xml").body(controlGenerator.generate(requestData).payload());
    }
}
