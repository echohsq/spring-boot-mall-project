package com.example.mall.service.impl;

import com.example.mall.mbg.mapper.PmsBrandMapper;
import com.example.mall.mbg.model.PmsBrand;
import com.example.mall.mbg.model.PmsBrandExample;
import com.example.mall.service.PmsBrandService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 增删改查操作 实现类
 * @author hsqzs
 * date 2020/8/26 14:14
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {
    @Autowired
    PmsBrandMapper pmsBrandMapper;
    /**
     * 增
     *
     * @param brand 品牌类
     * @return 操作值， 1， 0
     */
    @Override
    public int createBrand(PmsBrand brand) {
        return pmsBrandMapper.insertSelective(brand);
    }

    /**
     * 删
     *
     * @param id 主键
     * @return 操作值， 1， 0
     */
    @Override
    public int delectBrand(Long id) {
        return pmsBrandMapper.deleteByPrimaryKey(id);
    }

    /**
     * 改
     *
     * @param id    主键
     * @param brand 品牌类
     * @return 操作值， 1， 0
     */
    @Override
    public int updateBrand(Long id, PmsBrand brand) {
        brand.setId(id);
        return pmsBrandMapper.updateByPrimaryKeySelective(brand);
    }

    /**
     * 查全部
     *
     * @return 操作值， 1， 0
     */
    @Override
    public List<PmsBrand> listAllBrands() {
        return pmsBrandMapper.selectByExample(new PmsBrandExample());
    }

    /**
     * 查单个
     *
     * @param id 主键
     * @return 操作值， 1， 0
     */
    @Override
    public PmsBrand getBrand(Long id) {
        return pmsBrandMapper.selectByPrimaryKey(id);
    }

    /**
     * 分页查询
     *
     * @param pageNum  页面数量
     * @param pageSize 页面大小
     * @return 商品类列表
     */
    @Override
    public List<PmsBrand> listBrand(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        return pmsBrandMapper.selectByExample(new PmsBrandExample());
    }
}
