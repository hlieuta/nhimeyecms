package com.nhimeye.data.domain;

import com.nhimeye.data.reference.FieldType;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.layers.repository.mongo.RooMongoEntity;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RooJavaBean
@RooToString
@RooMongoEntity
public class Field {

    @NotNull
    @Column(unique = true)
    @Size(min = 2,max = 125)
    private String name;
    
    @Size(max = 255)
    private String description;

    @NotNull
    private int min;

    @NotNull
    private int max;
    
    private String defaultValue;
    
    @Min(0)
    private int maxLength;

    @NotNull
    @Enumerated
    private FieldType fieldType;

}
