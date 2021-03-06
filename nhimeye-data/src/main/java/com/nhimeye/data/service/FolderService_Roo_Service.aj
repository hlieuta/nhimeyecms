// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.nhimeye.data.service;

import com.nhimeye.data.domain.Folder;
import com.nhimeye.data.service.FolderService;
import java.math.BigInteger;
import java.util.List;

privileged aspect FolderService_Roo_Service {
    
    public abstract long FolderService.countAllFolders();    
    public abstract void FolderService.deleteFolder(Folder folder);    
    public abstract Folder FolderService.findFolder(BigInteger id);    
    public abstract List<Folder> FolderService.findAllFolders();    
    public abstract List<Folder> FolderService.findFolderEntries(int firstResult, int maxResults);    
    public abstract void FolderService.saveFolder(Folder folder);    
    public abstract Folder FolderService.updateFolder(Folder folder);    
}
