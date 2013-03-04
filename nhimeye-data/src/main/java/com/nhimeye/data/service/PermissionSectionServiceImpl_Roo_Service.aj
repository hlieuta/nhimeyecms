// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.nhimeye.data.service;

import com.nhimeye.data.domain.PermissionSection;
import com.nhimeye.data.repository.PermissionSectionRepository;
import com.nhimeye.data.service.PermissionSectionServiceImpl;
import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect PermissionSectionServiceImpl_Roo_Service {
    
    declare @type: PermissionSectionServiceImpl: @Service;
    
    declare @type: PermissionSectionServiceImpl: @Transactional;
    
    @Autowired
    PermissionSectionRepository PermissionSectionServiceImpl.permissionSectionRepository;
    
    public long PermissionSectionServiceImpl.countAllPermissionSections() {
        return permissionSectionRepository.count();
    }
    
    public void PermissionSectionServiceImpl.deletePermissionSection(PermissionSection permissionSection) {
        permissionSectionRepository.delete(permissionSection);
    }
    
    public PermissionSection PermissionSectionServiceImpl.findPermissionSection(BigInteger id) {
        return permissionSectionRepository.findOne(id);
    }
    
    public List<PermissionSection> PermissionSectionServiceImpl.findAllPermissionSections() {
        return permissionSectionRepository.findAll();
    }
    
    public List<PermissionSection> PermissionSectionServiceImpl.findPermissionSectionEntries(int firstResult, int maxResults) {
        return permissionSectionRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    public void PermissionSectionServiceImpl.savePermissionSection(PermissionSection permissionSection) {
        permissionSectionRepository.save(permissionSection);
    }
    
    public PermissionSection PermissionSectionServiceImpl.updatePermissionSection(PermissionSection permissionSection) {
        return permissionSectionRepository.save(permissionSection);
    }
    
}