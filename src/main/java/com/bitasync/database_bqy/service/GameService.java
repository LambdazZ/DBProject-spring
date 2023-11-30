package com.bitasync.database_bqy.service;

import com.bitasync.database_bqy.model.Game;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author Lambda
 * @date 2023/11/30
 */
public interface GameService
{
    Optional<Game> getGame(Integer id);
    Optional<List<Game>> getGameByTag(String tag);
}
