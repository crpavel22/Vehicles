package com.example.vehicles.service;

import com.example.vehicles.entity.AmphibianEntity;
import com.example.vehicles.utils.SearchCriteria;

import java.util.List;

public interface AmphibianService {

    List<AmphibianEntity> getAll(List<SearchCriteria> params);

    AmphibianEntity get(Integer id);

    boolean add(AmphibianEntity amphibianEntity);

    void remove(Integer id);

    AmphibianEntity update(AmphibianEntity amphibianEntity);
}
