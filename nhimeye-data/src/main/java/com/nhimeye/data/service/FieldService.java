package com.nhimeye.data.service;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.nhimeye.data.domain.Field;

@RooService(domainTypes = { com.nhimeye.data.domain.Field.class })
public interface FieldService {

    long countAllFields(String filter);

    List<Field> findAllFields(String filter, int firstResult, int pageSize);
}
