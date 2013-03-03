package com.nhimeye.data.service;

import java.util.List;

import com.nhimeye.data.domain.Field;


public class FieldServiceImpl implements FieldService {

    @Override
    public long countAllFields(String filter) {
        return fieldRepository.count();
    }


    @Override
    public List<Field> findAllFields(String filter, int firstResult,
            int pageSize) {
        return fieldRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / pageSize, pageSize)).getContent();
    }


}
