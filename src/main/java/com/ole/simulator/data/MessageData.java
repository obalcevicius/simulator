package com.ole.simulator.data;

public record MessageData(String documentId,
                          String messageId,
                          String transactionId,
                          String e2eReference,
                          String messageType,
                          String initTimestamp,
                          String payload) {
    public MessageData (String id, String payload) {
        this(id, null, null, null, null, null, payload);
    }

}
