package com.nhimeye.data.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.layers.repository.mongo.RooMongoEntity;
import org.springframework.roo.addon.tostring.RooToString;

import com.nhimeye.data.reference.UserProvider;

@RooJavaBean
@RooToString
@RooMongoEntity
public class User {

    @NotNull
    @Column(unique = true)
    @Size(max = 25)
    private String userName;

    @NotNull
    @Size(max = 16)
    private String password;

    @NotNull
    @Size(max = 25)
    private String firstName;

    @Size(max = 25)
    private String lastName;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+")
    private String email;

    @Size(max = 15)
    private String phone;

    @NotNull
    private Boolean active;

    @NotNull
    @Enumerated
    private UserProvider provider;

    private Set<java.math.BigInteger> roleIds = new HashSet<java.math.BigInteger>();

    public Set<java.math.BigInteger> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Set<java.math.BigInteger> roleIds) {
        this.roleIds = roleIds;
    }

}
