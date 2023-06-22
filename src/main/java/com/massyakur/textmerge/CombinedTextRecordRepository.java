package com.massyakur.textmerge;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CombinedTextRecordRepository extends MongoRepository<CombinedTextRecord, String> {
}
