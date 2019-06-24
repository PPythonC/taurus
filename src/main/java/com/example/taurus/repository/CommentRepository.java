package com.example.taurus.repository;

import com.example.taurus.model.domain.Comment;
import com.example.taurus.model.domain.Resources;
import com.example.taurus.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 */
public interface CommentRepository extends BaseRepository<Comment,Long> {
    Page<Comment> findCommentByResources(Resources resources, Pageable pageable);
    List<Comment> findCommentByResources(Resources resources);
    @Query(value = "select * from taurus_comment order by comment_date desc limit 5",nativeQuery = true)
    List<Comment> findTopFive();
    Integer countAllBy();
    @Query(value = "select *from taurus_comment order by comment_date desc limit :li:",nativeQuery = true)
    List<Comment>getCommentByLimit(@Param(value = "li")int li);
}
