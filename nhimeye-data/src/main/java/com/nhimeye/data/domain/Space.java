package com.nhimeye.data.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.layers.repository.mongo.RooMongoEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooMongoEntity
public class Space {

    @NotNull
    @Column(unique = true)
    @Size(max = 125)
    private String name;

    @NotNull
    @Column(unique = true)
    @Size(max = 125)
    private String spaceKey;

    @Size(max = 255)
    private String spaceDomain;
    
    private final Set<java.math.BigInteger> recordTypeIds = new HashSet<java.math.BigInteger>();
}
