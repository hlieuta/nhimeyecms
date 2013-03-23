package com.nhimeye.data.domain;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.layers.repository.mongo.RooMongoEntity;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RooJavaBean
@RooToString
@RooMongoEntity
public class User {

    @NotNull
    @Column(unique = true)
    @Size(max = 25)
    private String userName;

    @NotNull
    @Size(max = 128)
    private String password;

    @NotNull
    @Size(max = 25)
    private String firstName;

    @Size(max = 25)
    private String lastName;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+")
    private String email;

    @NotNull
    private Boolean active;


}
