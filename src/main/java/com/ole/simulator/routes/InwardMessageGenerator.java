package com.ole.simulator.routes;

import com.ole.simulator.messaging.RequestProducer;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class InwardMessageGenerator  extends RouteBuilder {
    private final RequestProducer requestProducer;

    public InwardMessageGenerator(RequestProducer requestProducer) {
        this.requestProducer = requestProducer;
    }

    @Override
    public void configure() {
        from("seda:a?concurrentConsumers=10&size=2000")
                .routeId("inward-route")
                .delay(simple("${random(1500,2000)}"))
                .bean(RequestProducer.class, "send(${body})");
    }
}
