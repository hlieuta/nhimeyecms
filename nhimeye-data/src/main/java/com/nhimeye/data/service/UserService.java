package com.nhimeye.data.service;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.nhimeye.data.domain.User;

@RooService(domainTypes = { com.nhimeye.data.domain.User.class })
public interface UserService {

    User findByUserName(String string);

    long countAllUsers(String filter);

    List<User> findAllUsers(String filter, int firstResult, int pageSize);
}
