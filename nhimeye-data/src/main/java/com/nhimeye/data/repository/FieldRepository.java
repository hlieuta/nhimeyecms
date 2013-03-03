package com.nhimeye.data.repository;

import java.util.List;

import org.springframework.roo.addon.layers.repository.mongo.RooMongoRepository;

import com.nhimeye.data.domain.Field;

@RooMongoRepository(domainType = Field.class)
public interface FieldRepository {

    List<com.nhimeye.data.domain.Field> findAll();

}
