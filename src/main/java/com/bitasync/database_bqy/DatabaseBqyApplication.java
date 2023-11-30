package com.bitasync.database_bqy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.bitasync.database_bqy.dao"})
public class DatabaseBqyApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(DatabaseBqyApplication.class, args);
	}

}
