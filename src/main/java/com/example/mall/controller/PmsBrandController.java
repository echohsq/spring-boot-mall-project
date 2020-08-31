package com.example.mall.controller;

import com.example.mall.common.api.CommonPage;
import com.example.mall.common.api.CommonResult;
import com.example.mall.mbg.model.PmsBrand;
import com.example.mall.service.PmsBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@Controller
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    PmsBrandService pmsBrandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);
    /**
     * 不带/是相对路径，带/是绝对路径
      */
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> getBrandList () {
        return CommonResult.sucess(pmsBrandService.listAllBrands());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
        CommonResult result;
        int value = pmsBrandService.createBrand(pmsBrand);
        if (value == 1) {
            result = CommonResult.sucess(pmsBrand);
            LOGGER.info("createBrand success:{}", pmsBrand);
        }
        else {
            result = CommonResult.failed("操作失败");
            LOGGER.info("createBrand failed:{}", pmsBrand);
        }
        return result;
    }

    @RequestMapping(value = "/update{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateBrand(@PathVariable Long id, @RequestBody PmsBrand pmsBrand, BindingResult bindingResult) {
        CommonResult commonResult;
        int value = pmsBrandService.updateBrand(id, pmsBrand);
        if (value == 1) {
            commonResult = CommonResult.sucess(pmsBrand);
            LOGGER.info("updateBrand success:{}", pmsBrand);
        }
        else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.info("updateBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }

    @RequestMapping(value = "/delete{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delectBrand(@PathVariable("id") Long id, BindingResult bindingResult) {
        CommonResult commonResult;
        int value = pmsBrandService.delectBrand(id);
        if (value == 1) {
            commonResult = CommonResult.sucess(null);
            LOGGER.info("delectBrand success: id={}", id);
        }
        else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.info("delectBrand failed: id={}", id);
        }
        return commonResult;
    }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<PmsBrand> list = pmsBrandService.listBrand(pageNum, pageSize);
        return CommonResult.sucess(CommonPage.restPage(list));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> getBrand(@PathVariable("id") Long id) {
        return CommonResult.sucess(pmsBrandService.getBrand(id));
    }
}
