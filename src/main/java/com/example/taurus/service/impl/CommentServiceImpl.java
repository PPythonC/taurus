package com.example.taurus.service.impl;

import com.example.taurus.model.domain.Comment;
import com.example.taurus.model.domain.Resources;
import com.example.taurus.repository.CommentRepository;
import com.example.taurus.repository.base.BaseRepository;
import com.example.taurus.service.CommentService;
import com.example.taurus.service.base.AbstractCrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CommentServiceImpl extends AbstractCrudService<Comment,Long> implements CommentService {
    private static final String COMMENTS_CACHE_NAME="comments";
    private static final String POSTS_CACHE_NAME = "posts";
    private final CommentRepository commentRepository;
    protected CommentServiceImpl(CommentRepository commentRepository) {
        super(commentRepository);
        this.commentRepository=commentRepository;
    }
    @Override
    public Comment create(Comment comment){return super.create(comment);}
    @Override
    public Optional<Comment> remove(Long commentId) {
        final Optional<Comment> comment=this.fetchById(commentId);
        commentRepository.delete(comment.orElse(null));
        return comment;
    }



    @Override
    public Page<Comment> findCommentsByResources(Resources resources, Pageable pageable) {
        return commentRepository.findCommentByResources(resources,pageable);
    }

    @Override
    public List<Comment> findCommentsByResources(Resources resources) {
        return commentRepository.findCommentByResources(resources);
    }

    @Override
    public List<Comment> findCommentsLatest() {
        return commentRepository.findTopFive();
    }

    @Override
    public Integer getCount() {
        return commentRepository.countAllBy();
    }

    @Override
    public List<Comment> getRecentComments(int limit) {
        return commentRepository.getCommentByLimit(limit);
    }
}
