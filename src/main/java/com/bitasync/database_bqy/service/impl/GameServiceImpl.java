package com.bitasync.database_bqy.service.impl;

import com.bitasync.database_bqy.dao.CommentsMapper;
import com.bitasync.database_bqy.dao.GameMapper;
import com.bitasync.database_bqy.model.Game;
import com.bitasync.database_bqy.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Lambda
 * @date 2023/11/30
 */
@Service
public class GameServiceImpl implements GameService
{
    final GameMapper gameMapper;

    public GameServiceImpl(@Autowired GameMapper gameMapper)
    {
        this.gameMapper = gameMapper;
    }

    @Override
    public Optional<Game> getGame(Integer id)
    {
        return Optional.ofNullable(gameMapper.getGame(id));
    }

    @Override
    public Optional<List<Game>> getGameByTag(String tag)
    {
        return Optional.ofNullable(gameMapper.getGameByTag(tag));
    }
}
