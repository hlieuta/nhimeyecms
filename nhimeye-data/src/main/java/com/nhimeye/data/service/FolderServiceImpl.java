package com.nhimeye.data.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.nhimeye.data.domain.Folder;

public class FolderServiceImpl implements FolderService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Folder> findByParentId(BigInteger parentId) {
        return folderRepository.findByParentId(parentId);
    }

    @Override
    public Folder findByParentIdAndName(BigInteger id, String name) {
        return folderRepository.findByParentIdAndName(id, name);

    }

    @Override
    public void deleteFolder(Folder folder, boolean recursive) {
        if (recursive) {
            mongoTemplate.remove(
                    Query.query(new Criteria("path").regex("^"
                            + folder.getPath() + "[.]")), Folder.class);
            mongoTemplate.remove(folder);
        } else {
            mongoTemplate.remove(folder);
        }
    }

}
