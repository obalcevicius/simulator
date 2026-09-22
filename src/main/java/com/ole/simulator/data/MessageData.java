package com.ole.simulator.data;

import java.time.Instant;

public record MessageData(String documentId,
                          String messageId,
                          String transactionId,
                          String e2eReference,
                          String messageType,
                          String initTimestamp,
                          String debtorAgent,
                          String payload,
                          Long internalTimestamp) {
    public MessageData (String id, String payload) {
        this(id, null, null, null, null, null, null, payload, Instant.now().toEpochMilli());
    }

}
