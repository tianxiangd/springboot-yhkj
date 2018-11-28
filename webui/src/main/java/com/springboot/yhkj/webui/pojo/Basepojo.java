package com.springboot.yhkj.webui.pojo;

public class Basepojo {
    private Integer pageNum = 1;

    private Integer PageSize = 10;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return PageSize;
    }

    public void setPageSize(Integer pageSize) {
        PageSize = pageSize;
    }
}
