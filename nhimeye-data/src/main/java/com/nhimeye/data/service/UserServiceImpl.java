package com.nhimeye.data.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.nhimeye.data.domain.User;

public class UserServiceImpl implements UserService {

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);

    }

    @Override
    public long countAllUsers(String filter) {
        return userRepository.count();
    }

    @Override
    public List<User> findAllUsers(String filter, int firstResult, int pageSize) {
        Pageable pageSpecification = new PageRequest(firstResult, pageSize);
        return userRepository.findAll(pageSpecification).getContent();
    }
}
