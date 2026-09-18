package com.ole.simulator.generator;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.random.RandomGenerator;

public class DataGenerator {
    private static final String CHARS = "BCDFGHJKLMNPQRSTVWXYZ";

    public DataGenerator() {
        RandomGenerator random = RandomGenerator.getDefault();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyMMdd").withZone(ZoneId.systemDefault());
        DateTimeFormatter formatterTimeStamp = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss").withZone(ZoneId.systemDefault());



        String rand = random.ints(9, 0, CHARS.length())
                .mapToObj(CHARS::charAt)
                .collect(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append)
                .toString();

        Instant instant = Instant.now().truncatedTo(ChronoUnit.MILLIS);
        timestamp = instant.toString();
        date = formatterTimeStamp.format(instant);
        msgId = "O"+formatterDate.format(instant)+rand;

        txId = msgId;
    }

    private String msgId;
    private String timestamp;
    private String date;
    private String txId;


    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }
}
