package com.example.mall.service;

import com.example.mall.mbg.model.PmsBrand;

import java.util.List;

/**
 * 增删改查操作
 * @author hsqzs
 * date 2020/8/26 14:13
 */
public interface PmsBrandService {
    /**
     * 增
     * @param brand 品牌类
     * @return 操作值， 1， 0
     */
    int createBrand(PmsBrand brand);

    /**
     * 删
     * @param id 主键
     * @return 操作值， 1， 0
     */
    int delectBrand(Long id);

    /**
     * 改
     * @param id 主键
     * @param brand 品牌类
     * @return 操作值， 1， 0
     */
    int updateBrand(Long id, PmsBrand brand);

    /**
     * 查全部
     * @return 操作值， 1， 0
     */
    List<PmsBrand> listAllBrands();

    /**
     * 查单个
     * @param id 主键
     * @return 操作值， 1， 0
     */
    PmsBrand getBrand(Long id);

    /**
     * 分页查询
     * @param pageNum 页面数量
     * @param pageSize 页面大小
     * @return 商品类列表
     */
    List<PmsBrand> listBrand(int pageNum, int pageSize);
}
