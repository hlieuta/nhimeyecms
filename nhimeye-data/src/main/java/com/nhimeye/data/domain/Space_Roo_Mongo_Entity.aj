// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.nhimeye.data.domain;

import com.nhimeye.data.domain.Space;
import java.math.BigInteger;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Persistent;

privileged aspect Space_Roo_Mongo_Entity {
    
    declare @type: Space: @Persistent;
    
    @Id
    private BigInteger Space.id;
    
    public BigInteger Space.getId() {
        return this.id;
    }
    
    public void Space.setId(BigInteger id) {
        this.id = id;
    }
    
}
