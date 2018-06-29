package com.certManager.service;

import java.io.IOException;
import java.security.cert.X509Certificate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.certManager.model.Cert;
import com.certManager.repository.CertManagerRepository;
import com.nimbusds.jose.util.X509CertUtils;

@Service
public class CertManagerserviceImpl implements CertManagerservice {

	@Autowired
	private CertManagerRepository certmangRepo;

	@Override
	public Cert uploadNewCert(Cert cert) {
		return certmangRepo.save(cert);
	}

	@Override
	public Cert blockCert(String _id) {

		List<Cert> ls = new ArrayList<>();
		certmangRepo.findAll().forEach(ls::add);
		for (Cert cert : ls) {
			if (cert.getId().equals(_id)) {
				return cert;
			}
		}
		ls.clear();
		return null;
	}

	@Override
	public void undoBockedCert(String _id) {

		certmangRepo.findById(_id).ifPresent(cert -> {
			if (cert.getBlocked() == true) {
				cert.setBlocked(false);
				cert.setUnlockedDate(LocalDate.now());
				certmangRepo.save(cert);
			}
		});

	}

	@Override
	public boolean checkIfBlocked(Cert cert) {
		if (cert.getBlocked() == true) {
			return true;
		}
		return false;
	}

	@Override
	public List<Cert> findAllBlockedCerts() {
		List<Cert> ls = new ArrayList<>();
		List<Cert> blockList = new ArrayList<>();
		certmangRepo.findAll().forEach(ls::add);
		for (Cert crt : ls) {
			if (crt.getBlocked()) {
				blockList.add(crt);
			}
		}
		ls.clear();
		return blockList;
	}

	@Override
	public String getCertInString(String _id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cert> findAllcerts() {
		List<Cert> ls = new ArrayList<>();
		certmangRepo.findAll().forEach(ls::add);
		return ls;
	}

	
	public String storeCertEncoded(MultipartFile file) {
		
		try {

			// check if the file's name contains invalid characters.
			if (file.getName().contains("..")) {
				throw new certManagerStorageException("Sorry! Filename contains invalid path sequence " + file.getName());
			}
			if (file.getName().isEmpty()) {
				throw new certManagerStorageException("Failed to store empty certificate" + file.getName());
			}
			
			byte[] fileByte = file.getBytes();
			X509Certificate file1 = X509CertUtils.parse(fileByte);
			String encodeCert = X509CertUtils.toPEMString(file1);
			
			return encodeCert;
		

		} catch (IOException e) {
			throw new certManagerStorageException("Failed to store file" + file.getName(), e);
		}
	}
	
	
}
