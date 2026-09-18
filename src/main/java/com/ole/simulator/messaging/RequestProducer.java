package com.ole.simulator.messaging;

import com.ole.simulator.controller.CentrolinkController;
import com.ole.simulator.generator.IFPSRPRTGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;


@Service
public class RequestProducer {

    @Value("${simulator.uriBase}")
    private String uriBase;
    private final RestClient restClient;
    private final IFPSRPRTGenerator messageGenerator;


    public RequestProducer(RestClient restClient, IFPSRPRTGenerator messageGenerator) {
        this.restClient = restClient;
        this.messageGenerator = messageGenerator;
    }

    public void send(Document payload) throws Exception {


        ResponseEntity<String> response =  restClient.post()
                                                     .uri(uriBase +"/api/v1/centrolink-sepa-payment-service/sepaInstantInboundMessage").body(messageGenerator.generate(payload))
                                                     .retrieve().toEntity(String.class);
    }
}
