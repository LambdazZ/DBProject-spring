package com.bitasync.database_bqy.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Lambda
 * @date 2023/11/30
 */
@Data
public class Game
{
    private Integer id;
    private String name;
    private Integer vender_id;
    private BigDecimal price;
    private String tag;
    private Double score;
    private String description;
    private String cover;
    private String picture1;
    private String picture2;
}
