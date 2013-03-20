package com.nhimeye.data.service;

import com.nhimeye.data.domain.Document;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigInteger;
import java.util.List;

public class DocumentServiceImpl implements DocumentService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Document> findByFolderId(BigInteger id) {
        return documentRepository.findByFolderId(id);
    }

    @Override
    public Document findByNameAndFolderId(String name, BigInteger folderId) {
        return documentRepository.findByNameAndFolderId(name, folderId);
    }

    @Override
    public List<Document> searchFile(String keyword, BigInteger selectedFolderId) {
        if (StringUtils.isEmpty(keyword)) {
            return documentRepository.findByFolderId(selectedFolderId);
        } else {
            return mongoTemplate.find(
                    Query.query(Criteria
                            .where("folderId")
                            .is(selectedFolderId)
                            .orOperator(
                                    Criteria.where("name").regex(keyword, "i"),
                                    Criteria.where("description").regex(
                                            keyword, "i"))), Document.class);
        }
    }

}
