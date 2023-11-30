package com.bitasync.database_bqy.dao;

import com.bitasync.database_bqy.model.Comments;
import com.bitasync.database_bqy.model.Game;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Lambda
 * @date 2023/11/30
 */
public interface GameMapper
{
    Game getGame(@Param("id") Integer id);
    List<Game> getGameByTag(@Param("tag") String tag);
}
