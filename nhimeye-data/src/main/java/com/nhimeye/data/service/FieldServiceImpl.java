package com.nhimeye.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class FieldServiceImpl implements FieldService {

    @Autowired
    MongoTemplate mongoTemplate;
}
