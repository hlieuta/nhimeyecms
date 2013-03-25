package com.nhimeye.data.domain;

import com.nhimeye.data.reference.Type;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.layers.repository.mongo.RooMongoEntity;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@RooJavaBean
@RooToString
@RooMongoEntity
public class RecordType {

    @NotNull
    @Column(unique = true)
    @Size(max = 125)
    private String name;

    @Size(max = 250)
    private String description;

    @NotNull
    @Enumerated
    private Type type;

    private  Set<java.math.BigInteger> fields = new HashSet<java.math.BigInteger>();
}
