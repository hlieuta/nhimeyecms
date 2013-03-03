package com.nhimeye.data.repository;

import com.nhimeye.data.domain.Role;
import java.util.List;
import org.springframework.roo.addon.layers.repository.mongo.RooMongoRepository;

@RooMongoRepository(domainType = Role.class)
public interface RoleRepository {

    List<com.nhimeye.data.domain.Role> findAll();
}
