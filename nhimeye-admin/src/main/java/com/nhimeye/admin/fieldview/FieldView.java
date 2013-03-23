package com.nhimeye.admin.fieldview;
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
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import com.google.common.eventbus.EventBus;
import com.nhimeye.admin.AbstractEntityView;
import com.nhimeye.data.domain.Field;
import com.nhimeye.data.reference.FieldType;
import com.nhimeye.data.service.FieldService;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Configurable(preConstruction = true)
public class FieldView extends AbstractEntityView<Field> {

    BeanContainer<BigInteger,Field>   container;

    @Autowired
    FieldService fieldService;

    @Override
    protected boolean deleteItems(Set<BigInteger> selectedValues) {
        //TODO: can not delete all by query due to current spring bug
        for(java.math.BigInteger id : selectedValues)
        {
             fieldService.deleteField(container.getItem(id).getBean());
        }
        return true;
    }

    @Override
    protected void configureTable(Table table) {
        table.setVisibleColumns(new Object[] { "name", "description", "fieldType"});
        table.setColumnHeader("name", "Name");
        table.setColumnHeader("description", "Description");
        table.setColumnHeader("fieldType","Field Type");
    }

    @Override
    protected Label getTitle() {
        return new Label("All Fields");
    }

    @Override
    protected BeanContainer<BigInteger,Field> getTableContainer() {
        if(container == null)
        {
            container = new BeanContainer<BigInteger,Field>(Field.class);
            container.setBeanIdProperty("id");

            for (Field entity : fieldService.findAllFields()) {
                container.addBean(entity);
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

            }
        };
    }

    @Override
    protected void createNewButtonClicked(EventBus eventBus) {
        Field field = new Field();
        field.setFieldType(FieldType.TextField);
        showWindow(field,eventBus);

    }

    @Override
    protected void refreshContainer() {

        container.removeAllItems();
        container = new BeanContainer<BigInteger,Field>(Field.class);
        container.setBeanIdProperty("id");

        for (Field entity : fieldService.findAllFields()) {
            container.addBean(entity);
        }
    }

    @Override
    protected void viewDetails(BigInteger id,EventBus eventBus) {
        Field field = container.getItem(id).getBean();
        showWindow(field,eventBus);
    }

    private void showWindow(Field field,EventBus eventBus)
    {
        Window w = new FieldDetailsWindow(field,eventBus);
        UI.getCurrent().addWindow(w);
        w.focus();
    }
}