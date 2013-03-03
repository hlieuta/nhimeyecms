package com.nhimeye.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhimeye.data.domain.User;

@Service
public class Assembler {

    @Transactional(readOnly = true)
    org.springframework.security.core.userdetails.User buildUserFromUserEntity(
            User userEntity) {

        String username = userEntity.getUserName();
        String password = userEntity.getPassword();
        boolean enabled = userEntity.getActive();
        boolean accountNonExpired = userEntity.getActive();
        boolean credentialsNonExpired = userEntity.getActive();
        boolean accountNonLocked = userEntity.getActive();

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        /*
         * for (Role role : userEntity.getRoles()) { authorities.add(new
         * SimpleGrantedAuthority(role.getName())); }
         */

        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
                username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);
        return user;
    }
}
