// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.nhimeye.data.repository;

import com.nhimeye.data.domain.RecordType;
import com.nhimeye.data.repository.RecordTypeRepository;
import java.math.BigInteger;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

privileged aspect RecordTypeRepository_Roo_Mongo_Repository {
    
    declare parents: RecordTypeRepository extends PagingAndSortingRepository<RecordType, BigInteger>;
    
    declare @type: RecordTypeRepository: @Repository;
    
}
