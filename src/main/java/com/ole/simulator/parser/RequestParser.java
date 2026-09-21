package com.ole.simulator.parser;

import com.ole.simulator.data.MessageData;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;

public class RequestParser {

    private final static String DOCUMENT_ID = "DocId";
    private final static String MESSAGE_ID = "MsgId";
    private final static String TRANSACTION_ID = "TxId";
    private final static String ORIGINAL_TRANSACTION_ID = "OrgnlTxId";
    private final static String E2E_REFERENCE = "EndToEndId";
    private final static String ORIGINAL_E2E_REFERENCE = "OrgnlEndToEndId";
    private final static String MESSAGE_TYPE = "Msg";
    private final static String ACCEPTANCE_DATETIME = "AccptncDtTm";


    public static MessageData parseRequestMessage(InputStream message) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLEventReader reader = factory.createXMLEventReader(message);


        while (reader.hasNext()) {
            XMLEvent nextEvent = reader.nextEvent();

            if (nextEvent.isStartElement()) {
                StartElement startElement = nextEvent.asStartElement();
                if(MESSAGE_TYPE.equals(startElement.getName().getLocalPart())) {
                    var messageType = startElement.getAttributeByName(new QName("Version")).getValue().substring(0, 8);
                    switch(messageType) {
                        case "pacs.008":
                            return parseCustomerCreditTransfer(reader);
                        case "pacs.002":
                            return parsePaymentStatusReport(reader);
                    }
                }
            }
        }
        return null;
    }

    private static MessageData parseCustomerCreditTransfer(XMLEventReader reader) throws XMLStreamException {
        String documentId = "";
        String messageId = "";
        String transactionId = "";
        String e2eReference = "";
        String messageType = "";
        String initTimestamp = "";
        while (reader.hasNext()) {
            XMLEvent nextEvent = reader.nextEvent();

            if (nextEvent.isStartElement()) {

                StartElement startElement = nextEvent.asStartElement();
                switch (startElement.getName().getLocalPart()) {
                    case DOCUMENT_ID:
                        nextEvent = reader.nextEvent();
                        documentId = nextEvent.asCharacters().getData();
                        break;
                    case MESSAGE_ID:
                        nextEvent = reader.nextEvent();
                        messageId = nextEvent.asCharacters().getData();
                        break;
                    case TRANSACTION_ID:
                        nextEvent = reader.nextEvent();
                        transactionId = nextEvent.asCharacters().getData();
                        break;
                    case E2E_REFERENCE:
                        nextEvent = reader.nextEvent();
                        e2eReference = nextEvent.asCharacters().getData();
                        break;
                    case ACCEPTANCE_DATETIME:
                        nextEvent = reader.nextEvent();
                        initTimestamp = nextEvent.asCharacters().getData();
                        break;
                }
            }
        }
        return new MessageData(documentId, messageId, transactionId, e2eReference, messageType, initTimestamp, null);

    }

    private static MessageData parsePaymentStatusReport(XMLEventReader reader) throws XMLStreamException {
        String documentId = "";
        String messageId = "";
        String transactionId = "";
        String e2eReference = "";
        String messageType = "";
        String initTimestamp = "";
        while (reader.hasNext()) {
            XMLEvent nextEvent = reader.nextEvent();

            if (nextEvent.isStartElement()) {

                StartElement startElement = nextEvent.asStartElement();
                switch (startElement.getName().getLocalPart()) {
                    case DOCUMENT_ID:
                        nextEvent = reader.nextEvent();
                        documentId = nextEvent.asCharacters().getData();
                        break;
                    case MESSAGE_ID:
                        nextEvent = reader.nextEvent();
                        messageId = nextEvent.asCharacters().getData();
                        break;
                    case ORIGINAL_TRANSACTION_ID:
                        nextEvent = reader.nextEvent();
                        transactionId = nextEvent.asCharacters().getData();
                        break;
                    case ORIGINAL_E2E_REFERENCE:
                        nextEvent = reader.nextEvent();
                        e2eReference = nextEvent.asCharacters().getData();
                        break;
                    case ACCEPTANCE_DATETIME:
                        nextEvent = reader.nextEvent();
                        initTimestamp = nextEvent.asCharacters().getData();
                        break;
                }
            }
        }
        return new MessageData(documentId, messageId, transactionId, e2eReference, messageType, initTimestamp, null);
    }

}
