package com.nhimeye.data.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.layers.repository.mongo.RooMongoEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooMongoEntity
public class Role {

    @NotNull
    @Column(unique = true)
    @Size(max = 125)
    private String name;

    @Size(max = 250)
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Permission> permissions = new HashSet<Permission>();
}
