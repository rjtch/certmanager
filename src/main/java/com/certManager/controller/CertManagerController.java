package com.certManager.controller;

import java.io.IOException;
import java.security.cert.X509Certificate;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.certManager.model.Cert;
import com.certManager.service.CertManagerservice;
import com.nimbusds.jose.util.X509CertUtils;

@RestController
public class CertManagerController {

	@Autowired
	private CertManagerservice crtManagservice;
	//private static String UPLOADED_FOLDER = "~/PST";

	private final static Logger LOGGER = LoggerFactory.getLogger(CertManagerController.class);

	@RequestMapping(value = "/internal/uploadcert", method = { RequestMethod.PUT }, consumes = {
			"application/json" }, produces = { "application/json" })
	@ResponseStatus(HttpStatus.CREATED)
	public void uploadCert(@RequestBody Cert cert, HttpServletRequest request, HttpServletResponse response) {

		Cert crt = this.crtManagservice.uploadNewCert(cert);
		response.setHeader("location", request.getRequestURL().append(crt.getId()).toString());
		LOGGER.debug(crt.getName() + "new uploaded certificate");
	}

	@RequestMapping(value = "/internal/blockcert/{_id}", method = { RequestMethod.GET }, produces = {
			"application/json" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Cert getblockCertById(@PathVariable("_id") String _id) throws Exception {
		Cert cert = this.crtManagservice.blockCert(_id);
		LOGGER.debug(cert.getName() + "has been locked");
		return cert;
	}

	@RequestMapping(value = "/internal/allblockedcerts", method = { RequestMethod.GET }, produces = {
			"application/json" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Cert> getAllBlockedCerts() throws Exception {
		return crtManagservice.findAllBlockedCerts();
	}

	@RequestMapping(value = "/internal/unlockcert/{_id}", method = { RequestMethod.PUT }, produces = {
			"application/json" })
	public void unlockCert(@PathVariable("_id") String _id) {
		crtManagservice.undoBockedCert(_id);
		LOGGER.debug("cert" + _id + "has been unlocked");
	}

	@RequestMapping(value = "/internal/allcerts", method = { RequestMethod.GET }, produces = { "application/json" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Cert> getAllCerts() throws Exception {
		return crtManagservice.findAllcerts();
	}

	/*
	 * =================================================================== FILES
	 * =============================================================================
	 * ================
	 */

	@RequestMapping(value = "/internal/uploadencodedcert", method = { RequestMethod.PUT },  produces = { "application/json" })
	public ResponseEntity<String> uploadEncodedCert( @RequestParam("file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		LOGGER.debug("single file upload");
		LOGGER.debug("filename : " + file.getOriginalFilename());
		LOGGER.debug("file's size : " + file.getSize());

		if (file.isEmpty()) {
			return new ResponseEntity<String>("please select a file!", HttpStatus.OK);
		}
		
		byte[] bytes = file.getBytes();
		X509Certificate certificate = X509CertUtils.parse(bytes);
		String encodeCert = X509CertUtils.toPEMString(certificate);
		//certificate.getSerialNumber();
		
		Cert cert = new Cert();
		
		cert.setName(file.getOriginalFilename());
		cert.setEncoded(encodeCert);
		cert.setArrivedDate(LocalDate.now());
		cert.setBlocked(false);
		cert.setLockeddate(null);
		cert.setUnlockedDate(null);
		cert.setSerialNumber(certificate.getSerialNumber());
		
		crtManagservice.uploadNewCert(cert);
		
		//String encoded = this.crtManagservice.storeCertEncoded(file);
		//response.setHeader("location", request.getRequestURL().append(cert.getId()).toString());
		//LOGGER.debug(encoded + "new uploaded encoded certificate");
		
		
		return new ResponseEntity<String>("successfully uploaded - " + file.getOriginalFilename(), new HttpHeaders(),
				HttpStatus.CREATED);

	}

}
