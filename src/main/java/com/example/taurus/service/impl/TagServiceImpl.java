package com.example.taurus.service.impl;

import com.example.taurus.model.domain.Tag;
import com.example.taurus.repository.TagRepository;
import com.example.taurus.repository.base.BaseRepository;
import com.example.taurus.service.TagService;
import com.example.taurus.service.base.AbstractCrudService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TagServiceImpl extends AbstractCrudService<Tag, Long> implements TagService {
    private static final String POST_CACHE_NAME = "posts";
    private final TagRepository tagRepository;

    protected TagServiceImpl(TagRepository tagRepository) {
        super(tagRepository);
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag removeById(Long tagId) {
        return super.removeById(tagId);
    }

    @Override
    public Tag findByTagUrl(String tagUrl) {
        return tagRepository.findTagByTagUrl(tagUrl);
    }

    @Override
    public Tag findTagByTagName(String tagName) {
        return tagRepository.findTagByTagName(tagName);
    }

    @Override
    public List<Tag> strListTagList(String tagList) {
        final String[] tags = tagList.split(",");
        final List<Tag> tagsList = new ArrayList<>();
        for (String tag : tags) {
            final Tag t = findTagByTagName(tag);
            Tag nt = null;
            if (t != null) {
                tagsList.add(t);
            } else {
                nt = new Tag();
                nt.setTagName(tag);
                tagsList.add(create(nt));
            }
        }
        return tagsList;
    }
}
