package com.certManager;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.certManager.model.Cert;
import com.certManager.repository.CertManagerRepository;
import com.certManager.service.CertManagerservice;


@SpringBootApplication
public class CertManagerApplication {
		
	@Bean
	CommandLineRunner init(CertManagerRepository certmarepo, CertManagerservice certService) {
		
		return args->{
			initCert(certmarepo);
		};
		
	}

	
	public void initCert(CertManagerRepository certmarepo) {
		Cert targo = new Cert("targo", null, LocalDate.now(), LocalDate.now(), null, true, " ");
		Cert dbank = new Cert("dbank", null, LocalDate.now(), null, LocalDate.now(), false, " ");
		Cert inkasso = new Cert("inkasso", null, LocalDate.now(), null, LocalDate.now(), false, " ");
		Cert consors = new Cert("consors", null, LocalDate.now(), LocalDate.now(), null, true, " ");
		Cert commerz = new Cert("commerz", null, LocalDate.now(), null, LocalDate.now(), false,  "");	

		certmarepo.deleteAll();

		List<Cert> certs = Arrays.asList(targo, dbank, inkasso, consors, commerz);
		
		List<Cert> saveAll = certmarepo.saveAll(certs);
		saveAll.forEach(certmarepo::save);
	}

	
	public static void main(String[] args) {
		SpringApplication.run(CertManagerApplication.class, args);
	}
}
