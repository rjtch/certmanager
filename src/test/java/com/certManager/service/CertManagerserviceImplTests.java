package com.certManager.service;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.certManager.CertManagerApplication;
import com.certManager.model.Cert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CertManagerApplication.class)
public class CertManagerserviceImplTests {
	
	@Autowired
	private CertManagerserviceImpl service;

	@Test
	public void testInsertCert() throws Exception{
		service.uploadNewCert( new Cert("fau", null, LocalDate.now(), LocalDate.now(), null, false, null));
	}
	
	@Test
	public void testStoreFile() throws Exception{
		
	}

}
