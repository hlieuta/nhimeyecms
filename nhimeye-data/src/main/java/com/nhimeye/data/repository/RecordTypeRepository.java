package com.nhimeye.data.repository;

import java.util.List;

import org.springframework.roo.addon.layers.repository.mongo.RooMongoRepository;

import com.nhimeye.data.domain.RecordType;
import com.nhimeye.data.reference.Type;

@RooMongoRepository(domainType = RecordType.class)
public interface RecordTypeRepository {

    List<com.nhimeye.data.domain.RecordType> findAll();

    List<com.nhimeye.data.domain.RecordType> findByType(Type type);
}
