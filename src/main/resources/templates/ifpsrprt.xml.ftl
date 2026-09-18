<?xml version="1.0" encoding="UTF-8"?>
<EDoc>
    <Msg ID="Edoc" Type="IFPSRPRT" Version="pacs.002.001.10.2025">
        <Header>
            <Sender>LIABLT2XMSD</Sender>
            <Receiver>CBSBLT26XXX</Receiver>
            <Priority>55</Priority>
            <MsgId>${msgId}</MsgId>
            <Date>${time}</Date>
            <System>LITAS-INST</System>
        </Header>
        <Docs>
            <Doc>
                <Header>
                    <DocId>${msgId}</DocId>
                    <Dates>
                        <Type>FormDoc</Type>
                        <Date>${time}</Date>
                    </Dates>
                    <Type>IFPSRPRT</Type>
                    <Priority>55</Priority>
                    <BusinessArea>SEPAINST</BusinessArea>
                </Header>
                <Ifpsrprt>
                    <Document>
                        <FIToFIPmtStsRpt>
                            <GrpHdr>
                                <MsgId>${msgId}</MsgId>
                                <CreDtTm>${timestamp}</CreDtTm>
                                <InstgAgt>
                                    <FinInstnId>
                                        <BICFI>EEUHEE2X</BICFI>
                                    </FinInstnId>
                                </InstgAgt>
                                <InstdAgt>
                                    <FinInstnId>
                                        <BICFI>CBSBLT26XXX</BICFI>
                                    </FinInstnId>
                                </InstdAgt>
                            </GrpHdr>
                            <OrgnlGrpInfAndSts>
                                <OrgnlMsgId>${origMsgId}</OrgnlMsgId>
                                <OrgnlMsgNmId>pacs.008.001.08</OrgnlMsgNmId>
                                <GrpSts>ACCP</GrpSts>
                            </OrgnlGrpInfAndSts>
                            <TxInfAndSts>
                                <StsId>${msgId}</StsId>
                                <OrgnlEndToEndId>${origE2EID}</OrgnlEndToEndId>
                                <OrgnlTxId>${origTxnId}</OrgnlTxId>
                                <AccptncDtTm>${accpTime}</AccptncDtTm>
                                <OrgnlTxRef>
                                    <PmtTpInf>
                                        <SvcLvl>
                                            <Cd>SEPA</Cd>
                                        </SvcLvl>
                                        <LclInstrm>
                                            <Cd>INST</Cd>
                                        </LclInstrm>
                                    </PmtTpInf>
                                    <DbtrAgt>
                                        <FinInstnId>
                                            <BICFI>CBSBLT26XXX</BICFI>
                                        </FinInstnId>
                                    </DbtrAgt>
                                </OrgnlTxRef>
                            </TxInfAndSts>
                        </FIToFIPmtStsRpt>
                    </Document>
                </Ifpsrprt>
            </Doc>
        </Docs>
    </Msg>
    <Signature xmlns="http://www.w3.org/2000/09/xmldsig#" Id="DS_361CEFFF63A3418B8B6523D02A33E7D4">
        <SignedInfo>
            <CanonicalizationMethod Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315"/>
            <SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#rsa-sha1"/>
            <Reference URI="">
                <Transforms>
                    <Transform Algorithm="http://www.w3.org/2000/09/xmldsig#enveloped-signature"/>
                </Transforms>
                <DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1"/>
                <DigestValue>Ap55C/T6SWb2MoEMmjnJMTRfIyw=</DigestValue>
            </Reference>
            <Reference URI="#SP_361CEFFF63A3418B8B6523D02A33E7D4"
                       Type="http://uri.etsi.org/01903/v1.1.1#SignedProperties">
                <DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1"/>
                <DigestValue>njl2RlR67/T63M6st/DbKaKYL8I=</DigestValue>
            </Reference>
        </SignedInfo>
        <SignatureValue>
            kktTIViEPgJauJ2+Wj/BXYheMhhPreDowp4QKnBvE3cVj/IwfttoOvXCRSTEKK6M10c9MxtGX2hGb0U6pcGB5hpAgInd18hh4NsOJ04S3/Yk3kvTOUBO4u83HdE0911wfIinhp+ON0SHD74bENqyO4akMGPsRbv5cvXnEgQYePVOCOfODe0QxSYWnltUTFeAe+26WPq9uZzcyN2cEyLRlD4M1hr8HCz3a1xYWhlvjRLpyNPBPwlBOC9zUpSkQyH77QRAftrOIPJHoRE50MosV0KTj5sWfo8COuOXfOpMGtX9ttcenXz3mej0UUK7EV83X9Ehht146S0txUpQ4djFKA==
        </SignatureValue>
        <ds:Object xmlns="http://uri.etsi.org/01903/v1.1.1#" xmlns:ds="http://www.w3.org/2000/09/xmldsig#">
            <QualifyingPropertiesReference URI="http://www.lb.lt/pki/cert/?8qgOegrfKqAjvODPkq7ctJm4YHc="/>
            <QualifyingPropertiesReference URI="http://www.lb.lt/pki/crl/?j2NKFVtP8S5UAUleFc1sOHbsTjk="/>
            <QualifyingProperties Target="#DS_361CEFFF63A3418B8B6523D02A33E7D4">
                <SignedProperties Id="SP_361CEFFF63A3418B8B6523D02A33E7D4">
                    <SignedSignatureProperties>
                        <SigningTime>2026-07-01T11:06:39+03:00</SigningTime>
                        <SigningCertificate>
                            <Cert>
                                <CertDigest>
                                    <DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1"/>
                                    <DigestValue>8qgOegrfKqAjvODPkq7ctJm4YHc=</DigestValue>
                                </CertDigest>
                                <IssuerSerial>
                                    <ds:X509IssuerName>C=LT, L=Vilnius, O=Lietuvos bankas, OU=MSD, CN=LB-LITAS-CA
                                    </ds:X509IssuerName>
                                    <ds:X509SerialNumber>347599381669548201607413</ds:X509SerialNumber>
                                </IssuerSerial>
                            </Cert>
                        </SigningCertificate>
                        <SignaturePolicyIdentifier>
                            <SignaturePolicyImplied/>
                        </SignaturePolicyIdentifier>
                    </SignedSignatureProperties>
                </SignedProperties>
                <UnsignedProperties>
                    <UnsignedSignatureProperties>
                        <SignatureTimeStamp>
                            <HashDataInfo uri="#DS_361CEFFF63A3418B8B6523D02A33E7D4">
                                <Transforms>
                                    <ds:Transform Algorithm="http://www.w3.org/TR/1999/REC-xpath-19991116">
                                        <ds:XPath xmlns:dsig="http://www.w3.org/2000/09/xmldsig#">
                                            ancestor-or-self::dsig:SignatureValue
                                        </ds:XPath>
                                    </ds:Transform>
                                </Transforms>
                            </HashDataInfo>
                            <EncapsulatedTimeStamp Id="TST_706715FFE13CA710CB7A20380A44FB0B658D075B">
                                MIICaAYJKoZIhvcNAQcCoIICWTCCAlUCAQMxCzAJBgUrDgMCGgUAMGoGCyqGSIb3DQEJEAEEoFsEWTBXAgEBBgYEAM4PAQEwITAJBgUrDgMCGgUABBRADoigsLy6Ol50qmGhkWaN0IcdmwIRAPfXQu/m9/KoQBYJaSFXA9YYDzIwMjYwNzAxMDgwNjM5WjADAgEBMYIB1TCCAdECAQEwazBdMQswCQYDVQQGEwJMVDEQMA4GA1UEBxMHVmlsbml1czEYMBYGA1UEChMPTGlldHV2b3MgYmFua2FzMQwwCgYDVQQLEwNNU0QxFDASBgNVBAMTC0xCLUxJVEFTLUNBAgpJm/ClAAAAAAD2MAkGBSsOAwIaBQCgQTAaBgkqhkiG9w0BCQMxDQYLKoZIhvcNAQkQAQQwIwYJKoZIhvcNAQkEMRYEFFS07wXdIt9lfqg9DtRgPEt5XnsyMA0GCSqGSIb3DQEBAQUABIIBAIuclnothiZJdBc6D31s5odpLDV2DQh4BXT64WM256ho2I7vUuEyzYCC/7EaOy7kU0/RKI8PhfjZyqJAo0vTQglRLR5vCs7VsBcRfp42C7TysLYw76a5Sn6G0iquTMxp7gACEERe5aD3V5NBpwNIDl+T1XHv9LE1OEWs28BJ5BV045J0NCj5/5vmcJwvbXwe6RQxhXSNzWMqT1kyAPr9PfLsqo/ro6WI5rB6aJrWRUiMwlWZe+Va1OBVAiEGlhKgHkWbUDOBvpPSB/+GagQdzq9O6JNtPR4dj8xMeks+JqZ4tvKa1EZkLGoYTSZENav9+VaShWlBXUvNpThF267oYIg=
                            </EncapsulatedTimeStamp>
                        </SignatureTimeStamp>
                    </UnsignedSignatureProperties>
                </UnsignedProperties>
            </QualifyingProperties>
        </ds:Object>
    </Signature>
</EDoc>