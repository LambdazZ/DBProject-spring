package com.bitasync.database_bqy.controller;

import com.bitasync.database_bqy.config.ApiResponse;
import com.bitasync.database_bqy.model.Comments;
import com.bitasync.database_bqy.model.Game;
import com.bitasync.database_bqy.service.CommentsService;
import com.bitasync.database_bqy.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Lambda
 * @date 2023/11/29
 */
@RestController
public class CommentsController
{
    final static Map<Integer, Integer> SCORE_TO_WEIGHT;
    final CommentsService commentsService;
    final GameService gameService;

    static
    {
        SCORE_TO_WEIGHT = new HashMap<>()
        {{
            put(1, -2);
            put(2, -1);
            put(3, 0);
            put(4, 1);
            put(5, 2);
        }};
    }

    public CommentsController(@Autowired CommentsService commentsService, @Autowired GameService gameService)
    {
        this.commentsService = commentsService;
        this.gameService = gameService;
    }

    @GetMapping(value = "/comments/getByUserId")
    ApiResponse getCommentsByUserId(@RequestParam("userId") Integer userId)
    {
        List<Comments> commentsByGameId = commentsService.getCommentsByUserId(userId).get();
        if(commentsByGameId.isEmpty())
            return ApiResponse.error(404, "目标不存在");
        return ApiResponse.ok(200, commentsByGameId);
    }

    @GetMapping(value = "/comments/getByGameId")
    ApiResponse getCommentsByGameId(@RequestParam("gameId") Integer gameId)
    {
        List<Comments> commentsByGameId = commentsService.getCommentsByGameId(gameId).get();
        if(commentsByGameId.isEmpty())
            return ApiResponse.error(404, "目标不存在");
        return ApiResponse.ok(200, commentsByGameId);
    }

    @GetMapping(value = "/comments/getRecommend")
    ApiResponse getRecommend(@RequestParam("userId") Integer userId)
    {
        try
        {
            Optional<List<Comments>> commentsByUserId = commentsService.getCommentsByUserId(userId);
            List<Comments> commentsList = commentsByUserId.orElse(Collections.emptyList());
            if(commentsList.isEmpty())
            {
                List<Game> gameByTag = gameService.getGameByTag("").get();
                List<Game> res = new ArrayList<>();
                for(int i = 0; i < 16; ++i)
                    res.add(gameByTag.get(i));
                return ApiResponse.ok(200, res);
            }
            Map<String, Integer> map = new HashMap<>();
            Set<Game> bought = new HashSet<>();
            for(var comment: commentsList)
            {
                Game game = gameService.getGame(comment.getGameId()).get();
                bought.add(game);
                String[] tags = game.getTag().replace(" ", "").split(",");
                for(var tag: tags)
                    map.put(tag, map.getOrDefault(tag, 0) + SCORE_TO_WEIGHT.get(comment.getScore()));
            }
            List<Game> res = new ArrayList<>();
            Set<Game> set = new HashSet<>();
            List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());
            entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
            for(int i = 0; i < 4 && i < entryList.size(); ++i)
            {
                int cnt = 0;
                String tag = entryList.get(i).getKey();
                List<Game> gameByTag = gameService.getGameByTag(tag).get();
                for(int j = 0; cnt < 1 << (3 - i) && j < gameByTag.size(); ++j)
                {
                    Game candidate = gameByTag.get(j);
                    if(!set.contains(candidate))
                    {
                        set.add(candidate);
                        if(!bought.contains(candidate))
                        {
                            res.add(candidate);
                            ++cnt;
                        }
                    }
                }
            }
            List<Game> games = gameService.getGameByTag("").get();
            for(int i = 0;res.size() < 16 && i < games.size(); ++i)
            {
                Game candidate = games.get(i);
                if (!set.contains(candidate))
                {
                    set.add(candidate);
                    if(!bought.contains(candidate))
                        res.add(candidate);
                }
            }

            return ApiResponse.ok(200, res);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ApiResponse.error(500, "内部错误");
        }
    }

    @PostMapping(value = "/comments/insert")
    ApiResponse insert(@RequestBody Comments comments)
    {
        commentsService.insertComments(comments);
        try
        {
            return ApiResponse.ok(200);
        }
        catch (Exception e)
        {
            return ApiResponse.error(500, "未知错误");
        }
    }

    @PostMapping(value = "/comments/update")
    ApiResponse update(@RequestBody Comments comments)
    {
        try
        {
            commentsService.updateComments(comments);
            return ApiResponse.ok(200);
        }
        catch (Exception e)
        {
            return ApiResponse.error(500, "未知错误");
        }
    }

    @PostMapping(value = "/comments/clear")
    ApiResponse clear(@RequestBody Comments comments)
    {
        try
        {
            commentsService.clearComments(comments);
            return ApiResponse.ok(200);
        }
        catch (Exception e)
        {
            return ApiResponse.error(500, "未知错误");
        }
    }
}
