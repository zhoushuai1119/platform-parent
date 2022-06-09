package com.cloud.platform.web.utils;

import org.apache.commons.collections.CollectionUtils;

import java.util.Iterator;
import java.util.List;

/**
 * @Description: 自定义迭代器MyIterator
 * @Author: ZhouShuai
 * @Date: 2021-06-04 10:49
 */
public abstract class MyIterator<T> implements Iterator<T> {

    /**
     * 页码
     */
    private int pageIndex = 1;
    /**
     * 页数
     */
    private int pageSize = 1000;
    /**
     * 当前索引
     */
    private int currentIndex;
    /**
     * 是否有更多数据
     */
    private boolean hasMore = true;
    /**
     * 数据集合
     */
    private List<T> list;

    public MyIterator() {

    }

    @Override
    public boolean hasNext() {
        if (!hasMore) {
            return false;
        }
        list = loadData(pageIndex, pageSize);
        //没有加载到数据，结束
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        //返回条数小于限制条数，表示没有更多的数据可以加载
        if (list.size() < pageSize) {
            hasMore = false;
        }
        currentIndex = 0;
        this.pageIndex++;
        return true;
    }

    @Override
    public T next() {
        return list.get(currentIndex++);
    }

    public List<T> getData() {
        return list;
    }

    /**
     * 初始化参数设置
     */
    public void init() {
        this.pageIndex = 1;
        hasMore = true;
        list = null;
    }

    public abstract List<T> loadData(int pageIndex, int pageSize);

}
