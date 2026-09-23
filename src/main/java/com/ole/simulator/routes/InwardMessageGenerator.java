package com.ole.simulator.routes;

import com.ole.simulator.controller.CentrolinkController;
import com.ole.simulator.messaging.RequestProducer;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


@Component
public class InwardMessageGenerator  extends RouteBuilder {

    @Value("${simulator.concurrentConsumers}")
    private String concurrentConsumers;

    @Value("${simulator.sedaMaxDepth}")
    private String sedaMaxDepth;

    @Value("${simulator.minDelay}")
    private Long minDelay;

    @Value("${simulator.maxDelay}")
    private String maxDelay;

    final Logger logger = LoggerFactory.getLogger(InwardMessageGenerator.class);

    public InwardMessageGenerator(RequestProducer requestProducer) {   }

    @Override
    public void configure() {
        from("seda:a?concurrentConsumers="+concurrentConsumers+"&size="+sedaMaxDepth)
                .routeId("inward-route")
                .process(exchange -> {
                    Long internalTimestamp = exchange.getProperty("internalTimestamp",Long.class);
                    String documentId = exchange.getProperty("documentID", String.class);
                    logger.debug("T1: {} {} ", documentId, Instant.now().toEpochMilli()-internalTimestamp);
                    if (0 == ChronoUnit.SECONDS.between(Instant.ofEpochMilli(internalTimestamp), Instant.now())) {
                        Thread.sleep(minDelay + (long) (Math.random() * 1000));
                    }
                    logger.debug("T2: {} {} ", documentId, Instant.now().toEpochMilli()-internalTimestamp);
                })
                .bean(RequestProducer.class, "send(${body})");
    }
}
