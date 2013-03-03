package com.nhimeye.data.domain;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.layers.repository.mongo.RooMongoEntity;
import org.springframework.roo.addon.tostring.RooToString;

import com.nhimeye.data.reference.FieldType;

@RooJavaBean
@RooToString
@RooMongoEntity
public class Field {

    @NotNull
    @Column(unique = true)
    @Size(max = 125)
    private String name;

    private int min;

    private int max;

    @NotNull
    @Enumerated
    private FieldType fieldType;

}
