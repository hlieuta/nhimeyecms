package com.nhimeye.data.service;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.nhimeye.data.domain.RecordType;
import com.nhimeye.data.reference.Type;

@RooService(domainTypes = { com.nhimeye.data.domain.RecordType.class })
public interface RecordTypeService {

    long countAllFileTypes(Type type, String filter);

    List<RecordType> findByTypes(Type type, String filter, int firstResult,
            int pageSize);

    List<RecordType> findByType(Type type);
}
