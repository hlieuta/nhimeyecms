// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.nhimeye.data.domain;

import com.nhimeye.data.domain.PermissionSectionDataOnDemand;
import com.nhimeye.data.domain.PermissionSectionIntegrationTest;
import com.nhimeye.data.service.PermissionSectionService;
import java.math.BigInteger;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

privileged aspect PermissionSectionIntegrationTest_Roo_IntegrationTest {
    
    declare @type: PermissionSectionIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: PermissionSectionIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    @Autowired
    private PermissionSectionDataOnDemand PermissionSectionIntegrationTest.dod;
    
    @Autowired
    PermissionSectionService PermissionSectionIntegrationTest.permissionSectionService;
    
    @Test
    public void PermissionSectionIntegrationTest.testCountAllPermissionSections() {
        Assert.assertNotNull("Data on demand for 'PermissionSection' failed to initialize correctly", dod.getRandomPermissionSection());
        long count = permissionSectionService.countAllPermissionSections();
        Assert.assertTrue("Counter for 'PermissionSection' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void PermissionSectionIntegrationTest.testFindPermissionSection() {
        PermissionSection obj = dod.getRandomPermissionSection();
        Assert.assertNotNull("Data on demand for 'PermissionSection' failed to initialize correctly", obj);
        BigInteger id = obj.getId();
        Assert.assertNotNull("Data on demand for 'PermissionSection' failed to provide an identifier", id);
        obj = permissionSectionService.findPermissionSection(id);
        Assert.assertNotNull("Find method for 'PermissionSection' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'PermissionSection' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void PermissionSectionIntegrationTest.testFindAllPermissionSections() {
        Assert.assertNotNull("Data on demand for 'PermissionSection' failed to initialize correctly", dod.getRandomPermissionSection());
        long count = permissionSectionService.countAllPermissionSections();
        Assert.assertTrue("Too expensive to perform a find all test for 'PermissionSection', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<PermissionSection> result = permissionSectionService.findAllPermissionSections();
        Assert.assertNotNull("Find all method for 'PermissionSection' illegally returned null", result);
        Assert.assertTrue("Find all method for 'PermissionSection' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void PermissionSectionIntegrationTest.testFindPermissionSectionEntries() {
        Assert.assertNotNull("Data on demand for 'PermissionSection' failed to initialize correctly", dod.getRandomPermissionSection());
        long count = permissionSectionService.countAllPermissionSections();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<PermissionSection> result = permissionSectionService.findPermissionSectionEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'PermissionSection' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'PermissionSection' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void PermissionSectionIntegrationTest.testSavePermissionSection() {
        Assert.assertNotNull("Data on demand for 'PermissionSection' failed to initialize correctly", dod.getRandomPermissionSection());
        PermissionSection obj = dod.getNewTransientPermissionSection(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'PermissionSection' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'PermissionSection' identifier to be null", obj.getId());
        permissionSectionService.savePermissionSection(obj);
        Assert.assertNotNull("Expected 'PermissionSection' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void PermissionSectionIntegrationTest.testDeletePermissionSection() {
        PermissionSection obj = dod.getRandomPermissionSection();
        Assert.assertNotNull("Data on demand for 'PermissionSection' failed to initialize correctly", obj);
        BigInteger id = obj.getId();
        Assert.assertNotNull("Data on demand for 'PermissionSection' failed to provide an identifier", id);
        obj = permissionSectionService.findPermissionSection(id);
        permissionSectionService.deletePermissionSection(obj);
        Assert.assertNull("Failed to remove 'PermissionSection' with identifier '" + id + "'", permissionSectionService.findPermissionSection(id));
    }
    
}