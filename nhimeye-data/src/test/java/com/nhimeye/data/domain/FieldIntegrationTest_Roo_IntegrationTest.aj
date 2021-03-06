// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.nhimeye.data.domain;

import com.nhimeye.data.domain.FieldDataOnDemand;
import com.nhimeye.data.domain.FieldIntegrationTest;
import com.nhimeye.data.service.FieldService;
import java.math.BigInteger;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

privileged aspect FieldIntegrationTest_Roo_IntegrationTest {
    
    declare @type: FieldIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: FieldIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    @Autowired
    private FieldDataOnDemand FieldIntegrationTest.dod;
    
    @Autowired
    FieldService FieldIntegrationTest.fieldService;
    
    @Test
    public void FieldIntegrationTest.testCountAllFields() {
        Assert.assertNotNull("Data on demand for 'Field' failed to initialize correctly", dod.getRandomField());
        long count = fieldService.countAllFields();
        Assert.assertTrue("Counter for 'Field' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void FieldIntegrationTest.testFindField() {
        Field obj = dod.getRandomField();
        Assert.assertNotNull("Data on demand for 'Field' failed to initialize correctly", obj);
        BigInteger id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Field' failed to provide an identifier", id);
        obj = fieldService.findField(id);
        Assert.assertNotNull("Find method for 'Field' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Field' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void FieldIntegrationTest.testFindAllFields() {
        Assert.assertNotNull("Data on demand for 'Field' failed to initialize correctly", dod.getRandomField());
        long count = fieldService.countAllFields();
        Assert.assertTrue("Too expensive to perform a find all test for 'Field', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Field> result = fieldService.findAllFields();
        Assert.assertNotNull("Find all method for 'Field' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Field' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void FieldIntegrationTest.testFindFieldEntries() {
        Assert.assertNotNull("Data on demand for 'Field' failed to initialize correctly", dod.getRandomField());
        long count = fieldService.countAllFields();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Field> result = fieldService.findFieldEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Field' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Field' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void FieldIntegrationTest.testSaveField() {
        Assert.assertNotNull("Data on demand for 'Field' failed to initialize correctly", dod.getRandomField());
        Field obj = dod.getNewTransientField(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Field' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Field' identifier to be null", obj.getId());
        fieldService.saveField(obj);
        Assert.assertNotNull("Expected 'Field' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void FieldIntegrationTest.testDeleteField() {
        Field obj = dod.getRandomField();
        Assert.assertNotNull("Data on demand for 'Field' failed to initialize correctly", obj);
        BigInteger id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Field' failed to provide an identifier", id);
        obj = fieldService.findField(id);
        fieldService.deleteField(obj);
        Assert.assertNull("Failed to remove 'Field' with identifier '" + id + "'", fieldService.findField(id));
    }
    
}
