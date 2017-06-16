package iochti.process.repository;

import iochti.process.document.IndicatorDocument;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IndicatorRepository extends MongoRepository<IndicatorDocument, String> {

}
