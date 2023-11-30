package com.bitasync.database_bqy.service;

import com.bitasync.database_bqy.model.Comments;

import java.util.List;
import java.util.Optional;

/**
 * @author Lambda
 * @date 2023/11/29
 */
public interface CommentsService
{
    Optional<Comments> getComments(Integer id);
    Optional<List<Comments>> getCommentsByUserId(Integer userId);
    Optional<List<Comments>> getCommentsByGameId(Integer gameID);

    void insertComments(Comments comments);

    void updateComments(Comments comments);

    void clearComments(Comments comments);
}
