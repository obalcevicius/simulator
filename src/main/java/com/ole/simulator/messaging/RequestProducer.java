package com.ole.simulator.messaging;

import com.ole.simulator.data.MessageData;
import com.ole.simulator.generator.IFPSRPRTGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;


@Service
public class RequestProducer {
    final Logger logger = LoggerFactory.getLogger(RequestProducer.class);

    @Value("${simulator.uriBase}")
    private String uriBase;
    private final RestClient restClient;
    private final IFPSRPRTGenerator messageGenerator;


    public RequestProducer(RestClient restClient, IFPSRPRTGenerator messageGenerator) {
        this.restClient = restClient;
        this.messageGenerator = messageGenerator;
    }

    public void send(MessageData request) throws Exception {
        logger.debug("T3: {} {} ", request.documentId(), Instant.now().toEpochMilli()-request.internalTimestamp());

        MessageData message = messageGenerator.generate(request);

        logger.info("Sent {}, originalID {}, elapsedTime {}", message.documentId(), request.documentId(), ChronoUnit.MILLIS.between(Instant.ofEpochMilli(request.internalTimestamp()), Instant.now()));
        logger.debug("T4: {} {} ", request.documentId(), Instant.now().toEpochMilli() - request.internalTimestamp());

        ResponseEntity<String> response =  restClient.post()
                                                     .uri(uriBase +"/api/v1/centrolink-sepa-payment-service/sepaInstantInboundMessage")
                                                     .contentType(MediaType.APPLICATION_XML)
                                                     .body(message.payload())
                                                     .retrieve().toEntity(String.class);
        logger.debug("T5: {} {}", request.documentId(), Instant.now().toEpochMilli() - request.internalTimestamp());
    }
}
