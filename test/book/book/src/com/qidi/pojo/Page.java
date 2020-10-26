package com.qidi.pojo;

import java.util.List;

/**
 * @author 白世鑫
 * @title: Page
 * @projectName Tomcat
 * @description:
 * @date 2020/9/3  9:48 上午
 */
public class Page<T> {

    public static final int PAGE_SIZE = 4;

    private String url;

    //当前页码
    private Integer pageNo;
    //当前页码显示的数量
    private Integer pageSize = PAGE_SIZE;
    //总记录数
    private Integer pageTotalCount;
    //总页码
    private Integer pageTotal;
    //当前页显示的数据
    private List<T> items;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Page() {
    }

    public Page(Integer pageNo, Integer pageSize, Integer pageTotalCount, Integer pageTotal, List<T> items) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.pageTotalCount = pageTotalCount;
        this.pageTotal = pageTotal;
        this.items = items;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "url='" + url + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", pageTotal=" + pageTotal +
                ", items=" + items +
                '}';
    }
}
