package com.nhimeye.data.domain;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.layers.repository.mongo.RooMongoEntity;
import org.springframework.roo.addon.tostring.RooToString;

import com.nhimeye.data.reference.EntityStatus;

@RooJavaBean
@RooToString
@RooMongoEntity
public class Document {

    @Size(min = 3, max = 260)
    private String name;

    private BigInteger docSetId;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date createDate;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date finalizeDate;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date deactivateDate;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date modifiedDate;

    @NotNull
    @Enumerated
    private EntityStatus status;

    private Boolean current;

    private BigInteger recoredTypeId;

    @NotNull
    private Integer size;

    @ManyToMany(cascade = CascadeType.ALL)
    private final Set<FieldValue> fieldValues = new HashSet<FieldValue>();

    private BigInteger folderId;

    private BigInteger ownerId;

    private String fileStoreId;

}
