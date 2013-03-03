package com.nhimeye.security;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import com.nhimeye.data.domain.User;
import com.nhimeye.data.service.UserService;

@Service
public class SpringSecurityHelper {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() throws Exception {
    }

    @PreDestroy
    public void destroy() {
    }

    public boolean hasAnyRole(SecurityContext context, String... roles) {
        Authentication authentication = context.getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication
                .getAuthorities();
        for (GrantedAuthority authority : authorities) {
            for (String role : roles) {
                if (role.equals(authority.getAuthority())) {
                    return true;
                }
            }
        }
        return false;
    }

    public User getUser(SecurityContext context) {
        return userService
                .findByUserName(context.getAuthentication().getName());
    }
}
