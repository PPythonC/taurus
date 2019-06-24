package com.example.taurus.service.impl;

import cn.hutool.core.util.StrUtil;
import com.example.taurus.model.domain.Options;
import com.example.taurus.repository.OptionsRepository;
import com.example.taurus.service.OptionService;
import com.example.taurus.service.base.AbstractCrudService;
import com.example.taurus.utils.ServiceUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@Service
public class OptionServiceImpl extends AbstractCrudService<Options, String> implements OptionService {
    private static final String POST_CACHE_NAME = "posts";
    private final OptionsRepository optionsRepository;

    public OptionServiceImpl(OptionsRepository optionsRepository) {
        super(optionsRepository);
        this.optionsRepository = optionsRepository;
    }

    @Override
    public void saveOption(String key, String value) {
//        use hutool
        if (StrUtil.equals(value, "")) {
            removeByIdOfNullable(key);
        } else if (StrUtil.isNotEmpty(key)) {
            Options options = fetchById(key).map(option -> {
                option.setOptionValue(value);
                return option;
            }).orElseGet(() -> {
                Options option = new Options();
                option.setOptionName(key);
                option.setOptionValue(value);
                return option;
            });
            optionsRepository.save(options);
        }
    }

    @Override
    public void saveOptions(Map<String, String> options) {
        if (!CollectionUtils.isEmpty(options)) {
            options.forEach(this::saveOption);
        }
    }

    @Override
    public Map<String, String> findAllOptions() {
        return ServiceUtils.convertToMap(listAll(), Options::getOptionName, Options::getOptionValue);
    }

    @Override
    public String findOneOption(String key) {
        return fetchById(key).map(Options::getOptionValue).orElse(null);
    }
}
