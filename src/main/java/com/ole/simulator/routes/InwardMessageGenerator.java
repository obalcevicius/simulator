package com.ole.simulator.routes;

import com.ole.simulator.messaging.RequestProducer;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class InwardMessageGenerator  extends RouteBuilder {

    @Value("${simulator.minDelay}")
    private String minDelay;

    @Value("${simulator.maxDelay}")
    private String maxDelay;

    public InwardMessageGenerator(RequestProducer requestProducer) {   }

    @Override
    public void configure() {
        from("seda:a?concurrentConsumers=10&size=2000")
                .routeId("inward-route")
                .delay(simple("${random("+minDelay+","+maxDelay+")}"))
                .bean(RequestProducer.class, "send(${body})");
    }
}
