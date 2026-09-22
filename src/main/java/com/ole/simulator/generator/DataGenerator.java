package com.ole.simulator.generator;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.random.RandomGenerator;

public class DataGenerator {
    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String ID_PREFIX = "OB";

    public DataGenerator() {
        RandomGenerator random = RandomGenerator.getDefault();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyMMdd").withZone(ZoneId.systemDefault());
        DateTimeFormatter formatterTimeStamp = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault());
        DateTimeFormatter formatterTimeStampFull = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.systemDefault());

        String rand = random.ints(8, 0, CHARS.length())
                .mapToObj(CHARS::charAt)
                .collect(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append)
                .toString();

        var instant = ZonedDateTime.now();
        timestamp = formatterTimeStampFull.format(instant);
        time = formatterTimeStamp.format(instant.truncatedTo(ChronoUnit.SECONDS));
        msgId = ID_PREFIX+formatterDate.format(instant)+rand;
    }

    private final String msgId;
    private final String timestamp;
    private final String time;


    public String getMsgId() {
        return msgId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getTime() {
        return time;
    }


}
