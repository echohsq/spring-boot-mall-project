package com.example.mall.common.api;


import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 分页数据封装类
 * @author hsqzs
 * date 2020/8/26 13:54
 */
public class CommonPage<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;

    private static <T> CommonPage<T> restPage(List<T> list) {
        CommonPage<T> rest = new CommonPage<>();
        PageInfo<T> info = new PageInfo<>(list);
        rest.setList(info.getList());
        rest.setPageNum(info.getPageNum());
        rest.setPageSize(info.getPageSize());
        rest.setTotal(info.getTotal());
        rest.setTotalPage(info.getPages());
        return rest;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
