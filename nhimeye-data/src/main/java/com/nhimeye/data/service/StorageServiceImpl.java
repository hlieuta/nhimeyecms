package com.nhimeye.data.service;

import java.io.InputStream;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

@Service("storageService")
public class StorageServiceImpl implements StorageService {

    @Autowired
    private GridFsOperations gridOperation;

    @Override
    public String save(InputStream inputStream, String filename) {
        DBObject metaData = new BasicDBObject();
        metaData.put("filename", filename);
        GridFSFile file = gridOperation.store(inputStream, filename, metaData);

        return file.getId().toString();
    }

    @Override
    public GridFSDBFile get(String id) {
        return gridOperation.findOne(Query.query(Criteria.where("_id").is(
                new ObjectId(id))));
    }

    @Override
    public void remove(String id) {
        gridOperation.delete(Query.query(Criteria.where("_id").is(
                new ObjectId(id))));

    }

}
