package com.nhimeye.admin;/*
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

import com.nhimeye.data.domain.Field;
import com.nhimeye.data.reference.FieldType;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;

import java.util.Arrays;
import java.util.Collection;

public class FieldDetailsView extends FormLayout {

    private FieldGroup fieldGroup = new FieldGroup();
    private Item item;
    private BeanItemContainer<FieldType> containerForFieldTypes;
    private FieldTypeView fieldTypeView;
    public FieldDetailsView(Field field)
    {
        item = new BeanItem<Field>(field);
        setWidth("35em");
        setSpacing(true);
        setMargin(true);
        final TextField fieldName = new TextField("Field Name:");
        final TextArea fieldDescription = new TextArea("Description:");

        final ComboBox fieldType = new ComboBox("Type:");
        fieldType.setImmediate(true);
        fieldType.setTextInputAllowed(false);
        fieldType.setContainerDataSource(getContainerForFieldTypes());
        fieldType.setItemCaptionMode(AbstractSelect.ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
        fieldGroup.bind(fieldName, "name");
        fieldGroup.bind(fieldDescription, "description");
        fieldGroup.bind(fieldType,"fieldType");

        addComponent(fieldName);
        addComponent(fieldDescription);
        addComponent(fieldType);

        fieldType.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                buildChildView((FieldType) event.getProperty().getValue());
            }
        });
        fieldGroup.setItemDataSource(getItem());

    }
    protected Item getItem() {
        return item;
    }

    private void buildChildView(FieldType type)
    {
       if(fieldTypeView!= null)
           fieldTypeView.removeComponents();
        switch (type) {
            case TextField:
                fieldTypeView = new TextFieldView(this);
                break;
            case RichTextArea:
                break;
            case NumberField:
                fieldTypeView = new NumberFieldView(this);
                break;
            case EmailField:
                break;

        }

    }


    public BeanItemContainer<FieldType> getContainerForFieldTypes() {
           if(containerForFieldTypes == null)
           {
            Collection<FieldType> items = Arrays.asList(FieldType.class
                    .getEnumConstants());
            BeanItemContainer<FieldType> container = new BeanItemContainer<FieldType>(
                    FieldType.class, items);
               containerForFieldTypes = container;
           }
            return containerForFieldTypes;

    }
}
