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
package com.nhimeye.admin;

import com.nhimeye.data.domain.User;
import com.nhimeye.data.service.FieldService;
import com.nhimeye.data.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ApplicationInitListener implements
        ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = Logger
            .getLogger(ApplicationInitListener.class);


    @Autowired
    FieldService fileService;

    @Autowired
    UserService userService;




    @Transactional
    private void createDefaultUser() {
        LOGGER.info("Checking for system user...");
        User defaultUser = userService.findByUserName("system");
        if (defaultUser == null) {
            defaultUser = new User();
            LOGGER.info("No user found in the system. Creating system default user ...");
            defaultUser.setActive(true);
            defaultUser.setFirstName("System");
            defaultUser.setLastName("User");
            defaultUser.setUserName("system");
            defaultUser.setPassword("password");
            defaultUser.setEmail("system@nhimeye.com");
            userService.addUser(defaultUser);
            LOGGER.info("Done.");
        }

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        try {
            createDefaultUser();
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }
}
