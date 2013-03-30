// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.nhimeye.data.service;

import com.nhimeye.data.domain.Space;
import com.nhimeye.data.service.SpaceService;
import java.math.BigInteger;
import java.util.List;

privileged aspect SpaceService_Roo_Service {
    
    public abstract long SpaceService.countAllSpaces();    
    public abstract void SpaceService.deleteSpace(Space space);    
    public abstract Space SpaceService.findSpace(BigInteger id);    
    public abstract List<Space> SpaceService.findAllSpaces();    
    public abstract List<Space> SpaceService.findSpaceEntries(int firstResult, int maxResults);    
    public abstract void SpaceService.saveSpace(Space space);    
    public abstract Space SpaceService.updateSpace(Space space);    
}
