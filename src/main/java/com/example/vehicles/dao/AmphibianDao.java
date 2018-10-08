package com.example.vehicles.dao;

import com.example.vehicles.entity.AmphibianEntity;
import com.example.vehicles.utils.SearchCriteria;

import java.util.List;

public interface AmphibianDao {
    List<AmphibianEntity> getAll();

    AmphibianEntity get(Integer id);

    void add(AmphibianEntity amphibianEntity);

    void remove(AmphibianEntity amphibianEntity);

    void alter(AmphibianEntity amphibianEntity);

    List<AmphibianEntity> findAll(List<SearchCriteria> params);
}
