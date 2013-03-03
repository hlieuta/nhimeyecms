package com.nhimeye.data.domain;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.layers.repository.mongo.RooMongoEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooMongoEntity
public class Permission {

    @NotNull
    @Column(unique = true)
    @Size(max = 125)
    private String name;

    @NotNull
    @Size(max = 250)
    private String description;
}
