package com.nhimeye.data.domain;

import java.math.BigInteger;

import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.layers.repository.mongo.RooMongoEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooMongoEntity
public class Folder {

    public Folder() {
    }

    public Folder(final String name, Folder parent) {
        this.name = name;
        parentId = parent.getId();
        path = parent.getPath() + "." + name;
    }

    @Size(min = 1, max = 260)
    private String name;

    @Size(min = 3, max = 260)
    private String path;

    private BigInteger parentId;
}
