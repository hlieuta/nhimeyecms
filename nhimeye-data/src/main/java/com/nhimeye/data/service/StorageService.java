package com.nhimeye.data.service;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.mongodb.gridfs.GridFSDBFile;

@Service("storageService")
public interface StorageService {

    String save(InputStream inputStream, String filename);

    GridFSDBFile get(String id);

    void remove(String id);

}
