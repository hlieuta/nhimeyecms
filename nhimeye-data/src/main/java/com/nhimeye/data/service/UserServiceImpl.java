package com.nhimeye.data.service;

import com.nhimeye.data.domain.User;
import com.nhimeye.data.domain.UserDetailsAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SaltSource saltSource;

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);

    }

    @Override
    public void addUser(User user) {
        UserDetailsAdapter userDetails = new UserDetailsAdapter(user);
        String password = userDetails.getPassword();
        Object salt = saltSource.getSalt(userDetails);
        user.setPassword(passwordEncoder.encodePassword(password, salt));
        saveUser(user);
    }

}
