package com.nhimeye.data.domain;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.layers.repository.mongo.RooMongoEntity;
import org.springframework.roo.addon.tostring.RooToString;

import com.nhimeye.data.reference.Type;

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

    private BigInteger iconId;

    @NotNull
    @Enumerated
    private Type type;

    @ManyToMany(cascade = CascadeType.ALL)
    private  Set<Field> fields = new HashSet<Field>();
}
