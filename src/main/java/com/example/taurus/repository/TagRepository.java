package com.example.taurus.repository;

import com.example.taurus.model.domain.Tag;
import com.example.taurus.repository.base.BaseRepository;

import java.util.List;

public interface TagRepository extends BaseRepository<Tag,Long> {
    Tag findTagByTagName(String tagName);
    Tag findTagByTagUrl(String tagUrl);
//    List<Tag> strListToTagList(String tagList);
}
