// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.nhimeye.data.repository;

import com.nhimeye.data.domain.User;
import com.nhimeye.data.repository.UserRepository;
import java.math.BigInteger;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

privileged aspect UserRepository_Roo_Mongo_Repository {
    
    declare parents: UserRepository extends PagingAndSortingRepository<User, BigInteger>;
    
    declare @type: UserRepository: @Repository;
    
}
