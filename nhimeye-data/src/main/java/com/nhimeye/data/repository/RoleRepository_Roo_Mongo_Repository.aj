// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.nhimeye.data.repository;

import com.nhimeye.data.domain.Role;
import com.nhimeye.data.repository.RoleRepository;
import java.math.BigInteger;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

privileged aspect RoleRepository_Roo_Mongo_Repository {
    
    declare parents: RoleRepository extends PagingAndSortingRepository<Role, BigInteger>;
    
    declare @type: RoleRepository: @Repository;
    
}