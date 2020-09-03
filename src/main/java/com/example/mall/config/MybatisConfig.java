package com.example.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis 配置类
 * @author hsqzs
 * date 2020/8/26 10:12
 */
@Configuration
@MapperScan({"com.example.mall.dao", "com.example.mall.mbg.mapper"})
public class MybatisConfig {
}
