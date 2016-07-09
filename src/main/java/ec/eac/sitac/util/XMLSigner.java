package ec.eac.sitac.util;

import java.util.Collections;

import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;

import eu.europa.esig.dss.DSSDocument;
import eu.europa.esig.dss.DigestAlgorithm;
import eu.europa.esig.dss.SignatureLevel;
import eu.europa.esig.dss.SignaturePackaging;
import eu.europa.esig.dss.SignatureValue;
import eu.europa.esig.dss.ToBeSigned;
import eu.europa.esig.dss.validation.CommonCertificateVerifier;
import eu.europa.esig.dss.xades.XAdESSignatureParameters;
import eu.europa.esig.dss.xades.signature.XAdESService;

public class XMLSigner {
	
	
static String signXADES(String xmlString) {
	String signedXmlString = xmlString;
/*	
	// Preparing parameters for the XAdES signature
	XAdESSignatureParameters parameters = new XAdESSignatureParameters();
	// We choose the level of the signature (-B, -T, -LT, -LTA).
	parameters.setSignatureLevel(SignatureLevel.XAdES_BASELINE_B);
	// We choose the type of the signature packaging (ENVELOPED, ENVELOPING, DETACHED).
	parameters.setSignaturePackaging(SignaturePackaging.ENVELOPED);
	// We set the digest algorithm to use with the signature algorithm. You must use the
	// same parameter when you invoke the method sign on the token. The default value is 	SHA256
	parameters.setDigestAlgorithm(DigestAlgorithm.SHA1);
	// We set the signing certificate
	parameters.setSigningCertificate(privateKey.getCertificate());
	// We set the certificate chain
	parameters.setCertificateChain(privateKey.getCertificateChain());
	
	// Create common certificate verifier
	CommonCertificateVerifier commonCertificateVerifier = new CommonCertificateVerifier();
	// Create XAdES service for signature
	XAdESService service = new XAdESService(commonCertificateVerifier);
	// Get the SignedInfo XML segment that need to be signed.
	ToBeSigned dataToSign = service.getDataToSign(toSignDocument, parameters);
	// This function obtains the signature value for signed information using the
	// private key and specified algorithm
	SignatureValue signatureValue = signingToken.sign(dataToSign, parameters
	.getDigestAlgorithm(), privateKey);
	// We invoke the service to sign the document with the signature value obtained in
	// the previous step.
	DSSDocument signedDocument = service.signDocument(toSignDocument, parameters,
	signatureValue);
	
	XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
	KeyInfoFactory kif = fac.getKeyInfoFactory(); 

	KeyValue kv = kif.newKeyValue(kp.getPublic());
	KeyInfo ki = kif.newKeyInfo(Collections.singletonList(kv)); 
	*/
	return signedXmlString;
}

}
