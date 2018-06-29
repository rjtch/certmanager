package com.certManager.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.certManager.model.Cert;

@Repository
public interface CertManagerRepository extends MongoRepository<Cert, String>{
	Optional<Cert> findById(String _id);
}
