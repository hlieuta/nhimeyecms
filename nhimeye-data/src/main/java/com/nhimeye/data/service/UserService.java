package com.nhimeye.data.service;

import com.nhimeye.data.domain.User;
import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { com.nhimeye.data.domain.User.class })
public interface UserService {

    User findByUserName(String username);

    void addUser(User user);

}
