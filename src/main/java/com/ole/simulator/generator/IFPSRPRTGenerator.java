package com.ole.simulator.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.StringWriter;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Service
public class IFPSRPRTGenerator {
    Logger logger = LoggerFactory.getLogger(IFPSRPRTGenerator.class);

    private final Configuration configuration;

    public IFPSRPRTGenerator(Configuration configuration) {
        this.configuration = configuration;
    }

    public String generate(Document payload) throws Exception {

        Template template = configuration.getTemplate("ifpsrprt.xml.ftl");

        XPath xPath = XPathFactory.newInstance().newXPath();
        Node txId = (Node) xPath.evaluate("/EDoc/Msg/Docs/Doc/Ifcctrns/Document/FIToFICstmrCdtTrf/CdtTrfTxInf/PmtId/TxId", payload, XPathConstants.NODE);
        Node docId = (Node) xPath.evaluate("/EDoc/Msg/Header/MsgId", payload, XPathConstants.NODE);
        Node msgId = (Node) xPath.evaluate("/EDoc/Msg/Docs/Doc/Ifcctrns/Document/FIToFICstmrCdtTrf/GrpHdr/MsgId", payload, XPathConstants.NODE);
        Node e2eID = (Node) xPath.evaluate("/EDoc/Msg/Docs/Doc/Ifcctrns/Document/FIToFICstmrCdtTrf/CdtTrfTxInf/PmtId/EndToEndId", payload, XPathConstants.NODE);
        Node accptTimestamp = (Node) xPath.evaluate("/EDoc/Msg/Docs/Doc/Ifcctrns/Document/FIToFICstmrCdtTrf/CdtTrfTxInf/AccptncDtTm", payload, XPathConstants.NODE);


        Map<String, Object> model = new HashMap<>();
        var data =  new DataGenerator();
        model.put("msgId", data.getMsgId());
        model.put("time",data.getDate());
        model.put("timestamp",data.getDate());
        model.put("origTxnId", txId.getTextContent());
        model.put("origE2EID",e2eID.getTextContent());
        model.put("accpTime",accptTimestamp.getTextContent());
        model.put("origMsgId",msgId.getTextContent());

        StringWriter writer = new StringWriter();
        template.process(model, writer);

        logger.info("Request {} sent at {}, OrgnlMsgId={}, OrgnlTxId={}", data.getMsgId(), Instant.now().truncatedTo(ChronoUnit.MILLIS).toString(), docId.getTextContent(), txId.getTextContent());

        return writer.toString();
    }
}
