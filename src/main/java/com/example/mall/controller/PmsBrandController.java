package com.example.mall.controller;

import com.example.mall.common.api.CommonResult;
import com.example.mall.mbg.model.PmsBrand;
import com.example.mall.service.PmsBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 品牌管理controller
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
            LOGGER.debug("createBrand success:{}", pmsBrand);
        }
        else {
            result = CommonResult.failed("操作失败");
            LOGGER.debug("createBrand failed:{}", pmsBrand);
        }
        return result;
    }
}
