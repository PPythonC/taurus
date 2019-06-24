package com.example.taurus.service;

import com.example.taurus.model.domain.Tag;
import com.example.taurus.service.base.CrudService;

import java.util.List;

public interface TagService extends CrudService<Tag,Long> {
    Tag findByTagUrl(String tagUrl);
    Tag findTagByTagName(String tagName);
    List<Tag> strListTagList(String tagList);
}
