package com.nhimeye.data.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.nhimeye.data.domain.Folder;

@RooService(domainTypes = { com.nhimeye.data.domain.Folder.class })
public interface FolderService {

    List<Folder> findByParentId(BigInteger parentId);

    Folder findByParentIdAndName(BigInteger id, String name);

    void deleteFolder(Folder folder, boolean b);
}
