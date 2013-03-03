package com.nhimeye.data.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.roo.addon.layers.repository.mongo.RooMongoRepository;

import com.nhimeye.data.domain.Document;

@RooMongoRepository(domainType = Document.class)
public interface DocumentRepository {

    List<com.nhimeye.data.domain.Document> findAll();

    List<com.nhimeye.data.domain.Document> findByFolderId(BigInteger folderId);

    com.nhimeye.data.domain.Document findByNameAndFolderId(String name,
            BigInteger folderId);
}
