package com.nhimeye.data.repository;

import java.util.List;

import org.springframework.roo.addon.layers.repository.mongo.RooMongoRepository;

import com.nhimeye.data.domain.User;

@RooMongoRepository(domainType = User.class)
public interface UserRepository {

    List<com.nhimeye.data.domain.User> findAll();

    com.nhimeye.data.domain.User findByUserName(String string);
}
