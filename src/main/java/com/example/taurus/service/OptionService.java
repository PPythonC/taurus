package com.example.taurus.service;

import com.example.taurus.model.domain.Options;
import com.example.taurus.service.base.CrudService;

import java.util.Map;

public interface OptionService extends CrudService<Options, String> {
    void saveOption(String key, String value);

    void saveOptions(Map<String, String> options);

    Map<String, String> findAllOptions();

    String findOneOption(String key);
}
