package com.nhimeye.data.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.nhimeye.data.domain.RecordType;
import com.nhimeye.data.reference.Type;

public class RecordTypeServiceImpl implements RecordTypeService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public long countAllFileTypes(Type type, String filter) {
        if (StringUtils.isEmpty(filter)) {
            return recordTypeRepository.count();
        } else {
            return mongoTemplate.count(
                    new Query(Criteria
                            .where("type")
                            .is(type)
                            .orOperator(
                                    Criteria.where("name").regex(filter, "i"),
                                    Criteria.where("description").regex(filter,
                                            "i"))), RecordType.class);

        }
    }

    @Override
    public List<RecordType> findByTypes(Type type, String filter,
            int firstResult, int pageSize) {

        if (StringUtils.isEmpty(filter)) {
            return recordTypeRepository.findByType(type);
        } else {
            return mongoTemplate
                    .find(new Query(Criteria
                            .where("type")
                            .is(type)
                            .orOperator(
                                    Criteria.where("name").regex(filter, "i"),
                                    Criteria.where("description").regex(filter,
                                            "i")))
                            .with(new org.springframework.data.domain.PageRequest(
                                    firstResult / pageSize, pageSize)),
                            RecordType.class);
        }
    }

    @Override
    public List<RecordType> findByType(Type type) {
        return recordTypeRepository.findByType(type);
    }

}
