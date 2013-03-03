package com.nhimeye.data.repository;

import com.nhimeye.data.domain.PermissionSection;
import java.util.List;
import org.springframework.roo.addon.layers.repository.mongo.RooMongoRepository;

@RooMongoRepository(domainType = PermissionSection.class)
public interface PermissionSectionRepository {

    List<com.nhimeye.data.domain.PermissionSection> findAll();
}
