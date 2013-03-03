package com.nhimeye.data.domain;

import java.math.BigInteger;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.layers.repository.mongo.RooMongoEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooMongoEntity
public class FieldValue {

    private BigInteger fieldId;

    private String data;
}
