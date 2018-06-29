package com.certManager.service;

import java.util.List;

import com.certManager.model.Cert;

public interface CertManagerservice {
	
	void undoBockedCert(String _id);
	
	boolean checkIfBlocked(Cert crt);
	
	List<Cert> findAllBlockedCerts();

	Cert uploadNewCert(Cert cert);

	Cert blockCert(String id);
	
	String getCertInString(String _id);
	
	List<Cert> findAllcerts();

}
