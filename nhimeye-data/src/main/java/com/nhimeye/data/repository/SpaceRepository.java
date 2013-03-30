package com.nhimeye.data.repository;

import com.nhimeye.data.domain.Space;
import java.util.List;
import org.springframework.roo.addon.layers.repository.mongo.RooMongoRepository;

@RooMongoRepository(domainType = Space.class)
public interface SpaceRepository {

    List<com.nhimeye.data.domain.Space> findAll();
}
