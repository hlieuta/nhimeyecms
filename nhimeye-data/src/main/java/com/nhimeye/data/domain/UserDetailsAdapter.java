package com.nhimeye.data.domain;
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

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;

public class UserDetailsAdapter extends org.springframework.security.core.userdetails.User {
    private String userName;
    public UserDetailsAdapter(User userEntity) {
        super(userEntity.getUserName(), userEntity.getPassword(), userEntity.getActive(), true, true, true, new ArrayList<GrantedAuthority>());
        userName = userEntity.getUserName();
    }

    public String getUserName()
    {
        return  userName;
    }




}
