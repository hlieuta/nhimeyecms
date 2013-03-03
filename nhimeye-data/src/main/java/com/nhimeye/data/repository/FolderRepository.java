package com.nhimeye.data.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.roo.addon.layers.repository.mongo.RooMongoRepository;

import com.nhimeye.data.domain.Folder;

@RooMongoRepository(domainType = Folder.class)
public interface FolderRepository {

    List<com.nhimeye.data.domain.Folder> findAll();

    List<com.nhimeye.data.domain.Folder> findByParentId(BigInteger parentId);

    com.nhimeye.data.domain.Folder findByParentIdAndName(BigInteger parentId,
            String name);
}
