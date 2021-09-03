package com.cloudshiba.pubsub.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class PageUtil {

    private static final String DESC = "descending";
    private static final String ASC = "ascending";

    public static <T> PageInfo<T> pageInfo(String pageNum, String pageSize, String sort, String order) {
        PageInfo<T> page = new PageInfo<>();
        page.setPageNum(Integer.parseInt(pageNum));
        page.setPageSize(Integer.parseInt(pageSize));
        PageHelper.orderBy(orderBy(sort, order));
        return page;
    }

    public static String orderBy(String sort, String order) {
        if (DESC.equals(order)) {
            order = " desc";
        } else if (ASC.equals(order)) {
            order = " asc";
        }
        return sort + order;
    }

}
