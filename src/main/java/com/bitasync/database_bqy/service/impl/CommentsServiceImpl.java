package com.bitasync.database_bqy.service.impl;

import com.bitasync.database_bqy.dao.CommentsMapper;
import com.bitasync.database_bqy.model.Comments;
import com.bitasync.database_bqy.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Lambda
 * @date 2023/11/29
 */
@Service
public class CommentsServiceImpl implements CommentsService
{
    final CommentsMapper commentsMapper;

    public CommentsServiceImpl(@Autowired CommentsMapper commentsMapper)
    {
        this.commentsMapper = commentsMapper;
    }

    @Override
    public Optional<Comments> getComments(Integer id)
    {
        return Optional.ofNullable(commentsMapper.getComments(id));
    }

    @Override
    public Optional<List<Comments>> getCommentsByUserId(Integer userId)
    {
        return Optional.ofNullable(commentsMapper.getCommentsByUserId(userId));
    }

    @Override
    public Optional<List<Comments>> getCommentsByGameId(Integer gameID)
    {
        return Optional.ofNullable(commentsMapper.getCommentsByGameId(gameID));
    }

    @Override
    public void insertComments(Comments comments)
    {
        commentsMapper.insertComments(comments);
    }

    @Override
    public void updateComments(Comments comments)
    {
        commentsMapper.updateComments(comments);
    }

    @Override
    public void clearComments(Comments comments)
    {
        commentsMapper.clearComments(comments);
    }
}
