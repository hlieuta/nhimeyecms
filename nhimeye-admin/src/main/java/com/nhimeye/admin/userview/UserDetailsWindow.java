package com.nhimeye.admin.userview;

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
import com.nhimeye.admin.AbstractEntityWindow;
import com.nhimeye.admin.event.NewItemAddedEvent;
import com.nhimeye.data.domain.User;
import com.nhimeye.data.service.UserService;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable(preConstruction = true)
public class UserDetailsWindow extends AbstractEntityWindow<User> {

    FormLayout formLayout;
    FieldGroup fieldGroup;
    private Item item;

    @Autowired
    UserService userService;

    public UserDetailsWindow(User entity, EventBus eventBus) {
        super(entity, eventBus);
        if(entity.getUserName() == null)
        {
            setCaption("New Field");
        } else
        {
            setCaption("Edit: " + entity.getUserName());
        }
    }

    @Override
    public Component buildComponents(User entity) {
        formLayout = new FormLayout();
        fieldGroup = new FieldGroup();
        formLayout.setWidth("35em");
        formLayout.setSpacing(true);
        formLayout.setMargin(true);
        item = new BeanItem<User>(entity);
        fieldGroup.setItemDataSource(item);

        TextField userName = fieldGroup.buildAndBind("Username:","userName",TextField.class);
        userName.setNullRepresentation("");
        userName.addValidator(new BeanValidator(User.class,"userName"));
        formLayout.addComponent(userName);

        PasswordField password = fieldGroup.buildAndBind("Password:","password",PasswordField.class);
        password.setNullRepresentation("");
        password.addValidator(new BeanValidator(User.class,"password"));
        formLayout.addComponent(password);


         TextField firstName = fieldGroup.buildAndBind("First Name:","firstName",TextField.class);
        firstName.setNullRepresentation("");
        firstName.addValidator(new BeanValidator(User.class,"firstName"));
        formLayout.addComponent(firstName);

        TextField lastName = fieldGroup.buildAndBind("Last Name:","lastName",TextField.class);
        lastName.setNullRepresentation("");
        lastName.addValidator(new BeanValidator(User.class,"lastName"));
        formLayout.addComponent(lastName);

        TextField email = fieldGroup.buildAndBind("Email:","email",TextField.class);
        email.setNullRepresentation("");
        email.addValidator(new BeanValidator(User.class,"email"));
        formLayout.addComponent(email);

        CheckBox active = fieldGroup.buildAndBind("Active","active",CheckBox.class);
        formLayout.addComponent(active);

        if(entity.getId() != null && entity.getUserName() != null)
        {
            userName.setEnabled(false);
        }

        return formLayout;
    }

    @Override
    protected boolean isModified() {
        return fieldGroup.isModified();
    }

    @Override
    protected boolean save(EventBus eventBus) {
        try
        {
            fieldGroup.commit();
            userService.addUser(getEntity());
            eventBus.post(new NewItemAddedEvent());
            return true;
        }catch (FieldGroup.CommitException e)
        {
            Notification
                    .show("Commit failed: " + e.getCause().getMessage(),
                            Notification.Type.TRAY_NOTIFICATION);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(e.getMessage(),e);
            }

        }
        return false;
    }

    public User getEntity() {

        if (item != null) {
            return ((BeanItem<User>) item).getBean();
        } else {
            return null;
        }
    }


}
