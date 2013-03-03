// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.nhimeye.data.domain;

import com.nhimeye.data.domain.Folder;
import com.nhimeye.data.domain.FolderDataOnDemand;
import com.nhimeye.data.service.FolderService;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect FolderDataOnDemand_Roo_DataOnDemand {
    
    declare @type: FolderDataOnDemand: @Component;
    
    private Random FolderDataOnDemand.rnd = new SecureRandom();
    
    private List<Folder> FolderDataOnDemand.data;
    
    @Autowired
    FolderService FolderDataOnDemand.folderService;
    
    public Folder FolderDataOnDemand.getNewTransientFolder(int index) {
        Folder obj = new Folder();
        setName(obj, index);
        setParentId(obj, index);
        setPath(obj, index);
        return obj;
    }
    
    public void FolderDataOnDemand.setName(Folder obj, int index) {
        String name = "name_" + index;
        if (name.length() > 260) {
            name = name.substring(0, 260);
        }
        obj.setName(name);
    }
    
    public void FolderDataOnDemand.setParentId(Folder obj, int index) {
        BigInteger parentId = BigInteger.valueOf(index);
        obj.setParentId(parentId);
    }
    
    public void FolderDataOnDemand.setPath(Folder obj, int index) {
        String path = "path_" + index;
        if (path.length() > 260) {
            path = path.substring(0, 260);
        }
        obj.setPath(path);
    }
    
    public Folder FolderDataOnDemand.getSpecificFolder(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Folder obj = data.get(index);
        BigInteger id = obj.getId();
        return folderService.findFolder(id);
    }
    
    public Folder FolderDataOnDemand.getRandomFolder() {
        init();
        Folder obj = data.get(rnd.nextInt(data.size()));
        BigInteger id = obj.getId();
        return folderService.findFolder(id);
    }
    
    public boolean FolderDataOnDemand.modifyFolder(Folder obj) {
        return false;
    }
    
    public void FolderDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = folderService.findFolderEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Folder' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Folder>();
        for (int i = 0; i < 10; i++) {
            Folder obj = getNewTransientFolder(i);
            try {
                folderService.saveFolder(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            data.add(obj);
        }
    }
    
}
