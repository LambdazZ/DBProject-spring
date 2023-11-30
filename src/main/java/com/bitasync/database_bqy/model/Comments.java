package com.bitasync.database_bqy.model;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Lambda
 * @date 2023/11/29
 */
@Data
public class Comments implements Serializable
{
    private Integer id;
    private String content;
    private Timestamp comment_time;
    private Integer score;
    private Integer userId;
    private Integer gameId;
    private String old_content;
}
