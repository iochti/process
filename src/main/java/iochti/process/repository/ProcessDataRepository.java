package iochti.process.repository;

import iochti.process.document.ProcessDataDocument;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProcessDataRepository extends MongoRepository<ProcessDataDocument, String> {
	
	List<ProcessDataDocument> findByCreatedAtGreaterThan(Date createdAt);
}
