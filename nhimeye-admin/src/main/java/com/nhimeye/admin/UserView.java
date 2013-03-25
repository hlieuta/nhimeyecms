package com.nhimeye.admin;
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
import com.nhimeye.data.domain.User;
import com.nhimeye.data.service.UserService;
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
public class UserView extends AbstractEntityView<User> {



    @Autowired
    UserService userService;

    @Override
    protected void configureTable(Table table) {
        table.setVisibleColumns(new Object[]{"userName", "firstName", "lastName", "email", "active"});
        table.setColumnHeader("userName", "User Name");
        table.setColumnHeader("firstName", "First Name");
        table.setColumnHeader("lastName", "Last Name");
        table.setColumnHeader("email", "Email");
    }

    @Override
    protected Label getTitle() {
        return new Label("All Users");
    }

    @Override
    protected BeanContainer<BigInteger, User> getTableContainer() {
        if (container == null) {
            container = new BeanContainer<BigInteger, User>(User.class);
            container.setBeanIdProperty("id");
            for (User user : userService.findAllUsers()) {
                container.addBean(user);
            }

        }
        return container;
    }

    @Override
    protected Set<String> getFilterProperties() {
        return new HashSet<String>(){
            {
                add("userName");
                add("firstName");
                add("lastName");
                add("email");
                add("active");


            }
        };
    }

    @Override
    protected void createNewButtonClicked(EventBus eventBus) {
        User user = new User();
        user.setActive(true);
        showWindow(user,eventBus);
    }

    @Override
    protected void refreshContainer() {
        container.removeAllItems();
        container = new BeanContainer<BigInteger,User>(User.class);
        container.setBeanIdProperty("id");

        for ( User entity : userService.findAllUsers()) {
            container.addBean(entity);
        }
    }

    @Override
    protected void viewDetails(BigInteger id, EventBus eventBus) {
        User user = container.getItem(id).getBean();
        showWindow(user,eventBus);
    }

    @Override
    protected boolean deleteItems(Set<BigInteger> selectedValues) {
        for (BigInteger id : selectedValues) {
            userService.deleteUser(container.getItem(id).getBean());
        }
        return true;
    }

    private void showWindow(User user,EventBus eventBus)
    {
        Window w = new UserEditor(user,eventBus);
        UI.getCurrent().addWindow(w);
        w.focus();
    }
}
