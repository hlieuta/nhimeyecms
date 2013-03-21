package com.nhimeye.security;

import com.nhimeye.data.domain.UserDetailsAdapter;
import com.nhimeye.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private Assembler assembler;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {
        UserDetails userDetails = null;
        com.nhimeye.data.domain.User userEntity = userService
                .findByUserName(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("user not found");
        }
        userDetails = new UserDetailsAdapter(userEntity);

        return userDetails;

    }
}