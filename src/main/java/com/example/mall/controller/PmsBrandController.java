package com.example.mall.controller;

import com.example.mall.common.api.CommonPage;
import com.example.mall.common.api.CommonResult;
import com.example.mall.mbg.model.PmsBrand;
import com.example.mall.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌管理controller
 * post 使用 @PathVariable ， get 使用 @RequestParam
 * @author hsqzs
 * date 2020/8/26 10:45
 */
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@Controller
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    PmsBrandService pmsBrandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);
    /**
     * 不带/是相对路径，带/是绝对路径
      */
    @ApiOperation("获取所有品牌列表")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> getBrandList () {
        return CommonResult.success(pmsBrandService.listAllBrands());
    }
    @ApiOperation("添加品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
        CommonResult result;
        int value = pmsBrandService.createBrand(pmsBrand);
        if (value == 1) {
            result = CommonResult.success(pmsBrand);
            LOGGER.info("createBrand success:{}", pmsBrand);
        }
        else {
            result = CommonResult.failed("操作失败");
            LOGGER.info("createBrand failed:{}", pmsBrand);
        }
        return result;
    }
    @ApiOperation("更新指定id品牌信息")
    @RequestMapping(value = "/update{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateBrand(@PathVariable Long id, @RequestBody PmsBrand pmsBrand, BindingResult bindingResult) {
        CommonResult commonResult;
        int value = pmsBrandService.updateBrand(id, pmsBrand);
        if (value == 1) {
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.info("updateBrand success:{}", pmsBrand);
        }
        else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.info("updateBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }
    @ApiOperation("删除指定id的品牌")
    @RequestMapping(value = "/delete{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delectBrand(@PathVariable("id") Long id, BindingResult bindingResult) {
        CommonResult commonResult;
        int value = pmsBrandService.delectBrand(id);
        if (value == 1) {
            commonResult = CommonResult.success(null);
            LOGGER.info("delectBrand success: id={}", id);
        }
        else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.info("delectBrand failed: id={}", id);
        }
        return commonResult;
    }
    @ApiOperation("分页查询品牌列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<PmsBrand> list = pmsBrandService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }
    @ApiOperation("获取指定id的品牌详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> getBrand(@PathVariable("id") Long id) {
        return CommonResult.success(pmsBrandService.getBrand(id));
    }

}
