// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.nhimeye.data.domain;

import com.nhimeye.data.domain.SpaceDataOnDemand;
import com.nhimeye.data.domain.SpaceIntegrationTest;
import com.nhimeye.data.service.SpaceService;
import java.math.BigInteger;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

privileged aspect SpaceIntegrationTest_Roo_IntegrationTest {
    
    declare @type: SpaceIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: SpaceIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    @Autowired
    private SpaceDataOnDemand SpaceIntegrationTest.dod;
    
    @Autowired
    SpaceService SpaceIntegrationTest.spaceService;
    
    @Test
    public void SpaceIntegrationTest.testCountAllSpaces() {
        Assert.assertNotNull("Data on demand for 'Space' failed to initialize correctly", dod.getRandomSpace());
        long count = spaceService.countAllSpaces();
        Assert.assertTrue("Counter for 'Space' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void SpaceIntegrationTest.testFindSpace() {
        Space obj = dod.getRandomSpace();
        Assert.assertNotNull("Data on demand for 'Space' failed to initialize correctly", obj);
        BigInteger id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Space' failed to provide an identifier", id);
        obj = spaceService.findSpace(id);
        Assert.assertNotNull("Find method for 'Space' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Space' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void SpaceIntegrationTest.testFindAllSpaces() {
        Assert.assertNotNull("Data on demand for 'Space' failed to initialize correctly", dod.getRandomSpace());
        long count = spaceService.countAllSpaces();
        Assert.assertTrue("Too expensive to perform a find all test for 'Space', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Space> result = spaceService.findAllSpaces();
        Assert.assertNotNull("Find all method for 'Space' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Space' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void SpaceIntegrationTest.testFindSpaceEntries() {
        Assert.assertNotNull("Data on demand for 'Space' failed to initialize correctly", dod.getRandomSpace());
        long count = spaceService.countAllSpaces();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Space> result = spaceService.findSpaceEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Space' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Space' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void SpaceIntegrationTest.testSaveSpace() {
        Assert.assertNotNull("Data on demand for 'Space' failed to initialize correctly", dod.getRandomSpace());
        Space obj = dod.getNewTransientSpace(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Space' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Space' identifier to be null", obj.getId());
        spaceService.saveSpace(obj);
        Assert.assertNotNull("Expected 'Space' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void SpaceIntegrationTest.testDeleteSpace() {
        Space obj = dod.getRandomSpace();
        Assert.assertNotNull("Data on demand for 'Space' failed to initialize correctly", obj);
        BigInteger id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Space' failed to provide an identifier", id);
        obj = spaceService.findSpace(id);
        spaceService.deleteSpace(obj);
        Assert.assertNull("Failed to remove 'Space' with identifier '" + id + "'", spaceService.findSpace(id));
    }
    
}
