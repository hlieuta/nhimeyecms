// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.nhimeye.data.domain;

import com.nhimeye.data.domain.Field;
import com.nhimeye.data.domain.FieldDataOnDemand;
import com.nhimeye.data.reference.FieldType;
import com.nhimeye.data.service.FieldService;
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

privileged aspect FieldDataOnDemand_Roo_DataOnDemand {
    
    declare @type: FieldDataOnDemand: @Component;
    
    private Random FieldDataOnDemand.rnd = new SecureRandom();
    
    private List<Field> FieldDataOnDemand.data;
    
    @Autowired
    FieldService FieldDataOnDemand.fieldService;
    
    public Field FieldDataOnDemand.getNewTransientField(int index) {
        Field obj = new Field();
        setDefaultValue(obj, index);
        setDescription(obj, index);
        setFieldType(obj, index);
        setMax(obj, index);
        setMaxLength(obj, index);
        setMin(obj, index);
        setName(obj, index);
        return obj;
    }
    
    public void FieldDataOnDemand.setDefaultValue(Field obj, int index) {
        String defaultValue = "defaultValue_" + index;
        obj.setDefaultValue(defaultValue);
    }
    
    public void FieldDataOnDemand.setDescription(Field obj, int index) {
        String description = "description_" + index;
        if (description.length() > 255) {
            description = description.substring(0, 255);
        }
        obj.setDescription(description);
    }
    
    public void FieldDataOnDemand.setFieldType(Field obj, int index) {
        FieldType fieldType = FieldType.class.getEnumConstants()[0];
        obj.setFieldType(fieldType);
    }
    
    public void FieldDataOnDemand.setMax(Field obj, int index) {
        int max = index;
        obj.setMax(max);
    }
    
    public void FieldDataOnDemand.setMaxLength(Field obj, int index) {
        int maxLength = index;
        if (maxLength < 0) {
            maxLength = 0;
        }
        obj.setMaxLength(maxLength);
    }
    
    public void FieldDataOnDemand.setMin(Field obj, int index) {
        int min = index;
        obj.setMin(min);
    }
    
    public void FieldDataOnDemand.setName(Field obj, int index) {
        String name = "name_" + index;
        if (name.length() > 125) {
            name = new Random().nextInt(10) + name.substring(1, 125);
        }
        obj.setName(name);
    }
    
    public Field FieldDataOnDemand.getSpecificField(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Field obj = data.get(index);
        BigInteger id = obj.getId();
        return fieldService.findField(id);
    }
    
    public Field FieldDataOnDemand.getRandomField() {
        init();
        Field obj = data.get(rnd.nextInt(data.size()));
        BigInteger id = obj.getId();
        return fieldService.findField(id);
    }
    
    public boolean FieldDataOnDemand.modifyField(Field obj) {
        return false;
    }
    
    public void FieldDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = fieldService.findFieldEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Field' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Field>();
        for (int i = 0; i < 10; i++) {
            Field obj = getNewTransientField(i);
            try {
                fieldService.saveField(obj);
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
