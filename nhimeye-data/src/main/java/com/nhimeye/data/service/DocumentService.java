package com.nhimeye.data.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.nhimeye.data.domain.Document;

@RooService(domainTypes = { com.nhimeye.data.domain.Document.class })
public interface DocumentService {

    List<Document> findByFolderId(BigInteger id);

    Document findByNameAndFolderId(String name, BigInteger folderId);

    List<Document> searchFile(String keyword, BigInteger selectedFolderId);
}
