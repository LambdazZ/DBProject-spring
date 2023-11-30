package com.bitasync.database_bqy.dao;

import com.bitasync.database_bqy.model.Comments;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Lambda
 * @date 2023/11/29
 */
public interface CommentsMapper
{
    Comments getComments(@Param("id") Integer id);

    List<Comments> getCommentsByUserId(@Param("userId") Integer userId);

    List<Comments> getCommentsByGameId(@Param("gameId") Integer gameId);

    void insertComments(Comments comments);

    void updateComments(Comments comments);

    void clearComments(Comments comments);
}
