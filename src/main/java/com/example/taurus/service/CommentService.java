package com.example.taurus.service;

import com.example.taurus.model.domain.Comment;
import com.example.taurus.model.domain.Resources;
import com.example.taurus.service.base.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

/**
 * 评论service
 */
public interface CommentService extends CrudService<Comment,Long> {
    @Deprecated
    Optional<Comment> remove(Long commentId);
    Page<Comment> findCommentsByResources(Resources resources,Pageable pageable);
    List<Comment> findCommentsByResources(Resources resources);
    List<Comment> findCommentsLatest();
    Integer getCount();
    List<Comment>getRecentComments(int limit);
}
