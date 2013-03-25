package com.nhimeye.admin;

import com.google.common.eventbus.EventBus;
import com.nhimeye.data.domain.RecordType;
import com.nhimeye.data.service.RecordTypeService;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/*
 * Copyright 2013 NHIMEYE Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the governing permissions and limitations under
 * the License.
 */

@Configurable(preConstruction = true)
public class RecordTypeView extends AbstractEntityView<RecordType> {

    @Autowired
    RecordTypeService recordTypeService;

    @Override
    protected void configureTable(Table table) {
        table.setVisibleColumns(new Object[]{"name","description","type"});
        table.setColumnHeader("name","Name");
        table.setColumnHeader("description","Description");
        table.setColumnHeader("type", "Type");
    }

    @Override
    protected Label getTitle() {
       return new Label("All Record Types");
    }

    @Override
    protected BeanContainer<BigInteger, RecordType> getTableContainer() {
        if (container == null) {
            container = new BeanContainer<BigInteger, RecordType>(RecordType.class);
            container.setBeanIdProperty("id");
            for (RecordType user : recordTypeService.findAllRecordTypes()) {
                container.addBean(user);
            }

        }
        return container;
    }

    @Override
    protected Set<String> getFilterProperties() {
        return new HashSet<String>(){
            {
                add("name");
                add("description");
                add("type");
            }
        };
    }

    @Override
    protected void createNewButtonClicked(EventBus eventBus) {

    }

    @Override
    protected void refreshContainer() {
        container.removeAllItems();
        container = new BeanContainer<BigInteger,RecordType>(RecordType.class);
        container.setBeanIdProperty("id");

        for ( RecordType entity : recordTypeService.findAllRecordTypes()) {
            container.addBean(entity);
        }
    }

    @Override
    protected void viewDetails(BigInteger id, EventBus eventBus) {

    }

    @Override
    protected boolean deleteItems(Set<BigInteger> selectedValues) {
       for(BigInteger id : selectedValues)
       {
           recordTypeService.deleteRecordType(container.getItem(id).getBean());
       }
        return true;
    }
}
