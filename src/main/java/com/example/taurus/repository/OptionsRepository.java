package com.example.taurus.repository;

import com.example.taurus.model.domain.Options;
import com.example.taurus.repository.base.BaseRepository;

public interface OptionsRepository extends BaseRepository<Options,String> {
    Options findOptionsByOptionName(String key);

}
