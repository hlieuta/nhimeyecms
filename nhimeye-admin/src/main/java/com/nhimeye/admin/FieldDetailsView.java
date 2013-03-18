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
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;


public class FieldDetailsView extends FormLayout {

    FieldGroup fieldGroup = new FieldGroup();
    private Item item;
    private BeanItemContainer<FieldType> containerForFieldTypes;
    private FieldTypeView fieldTypeView;
    public FieldDetailsView(Field field)
    {
        item = new BeanItem<Field>(field);
        setWidth("35em");
        setSpacing(true);
        setMargin(true);

        fieldGroup.setItemDataSource(getItem());
        TextField name = fieldGroup.buildAndBind("Name:","name",TextField.class);
        name.setNullRepresentation("");
        TextArea description = fieldGroup.buildAndBind("Description:","description",TextArea.class);
        description.setNullRepresentation("");
        ComboBox type = fieldGroup.buildAndBind("Field Type: ", "fieldType", ComboBox.class);
        type.setTextInputAllowed(false);
        type.setImmediate(true);
        type.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                buildChildView((FieldType) event.getProperty().getValue());
            }
        });
        name.addValidator(new BeanValidator(Field.class, "name"));
        description.addValidator(new BeanValidator(Field.class,"description"));
        addComponent(name);
        addComponent(description);
        addComponent(type);




    }
    private Item getItem() {
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

    public void commit() throws FieldGroup.CommitException {

        fieldGroup.commit();

    }

    public Field getEntity() {

        if (item != null) {
            return ((BeanItem<Field>) item).getBean();
        } else {
            return null;
        }
    }

}
