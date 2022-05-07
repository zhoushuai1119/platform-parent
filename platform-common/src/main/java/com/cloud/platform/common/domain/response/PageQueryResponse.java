package com.cloud.platform.common.domain.response;

import com.cloud.platform.common.exception.BaseExceptionCode;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @Description:
 * @Author: ZhouShuai
 * @Date: 2021-06-27 16:26
 */
@Data
public class PageQueryResponse<T> extends BaseResponse<List<T>> {

    private static final long serialVersionUID = -8937008601803151631L;

    /**
     * 页码
     */
    private Integer pageIndex;
    /**
     * 每页显示数量
     */
    private Integer pageSize;
    /**
     * 总记录条数
     */
    private long totalCount;

    public PageQueryResponse() {
    }

    public PageQueryResponse<T> successPage(List<T> model, Integer pageIndex, Integer pageSize, long totalCount) {
        this.setSuccess(true);
        this.setModel(model);
        this.setPageIndex(pageIndex);
        this.setPageSize(pageSize);
        this.setTotalCount(totalCount);
        return this;
    }


    public static <T> PageQueryResponse<T> createSuccessResult(List<T> model, Integer pageIndex, Integer pageSize, long totalCount) {
        PageQueryResponse<T> pageResponse = new PageQueryResponse();
        return pageResponse.successPage(model, pageIndex, pageSize, totalCount);
    }


    public static <T> PageQueryResponse<T> createPageFailResult(BaseExceptionCode errorCode) {
        PageQueryResponse<T> pageResponse = new PageQueryResponse();
        return (PageQueryResponse) pageResponse.fail(errorCode);
    }


    /**
     * 获取当前页
     * @return
     */
    public Integer getCurrentPage() {
        return this.pageIndex < 1 ? 1 : this.pageIndex;
    }

    /**
     * 是否有下一页
     * @return
     */
    public boolean hasNext() {
        long useCount = (this.getCurrentPage() - 1) * this.getPageSize() + this.getSize();
        return this.totalCount > useCount;
    }

    /**
     * 总页数
     * @return
     */
    public long getTotalPage() {
        return this.pageSize == 0 ? 0 : (this.totalCount - 1) / this.pageSize + 1;
    }

    /**
     * 当前查询到的记录数
     * @return
     */
    private long getSize() {
        List<T> page = this.getModel();
        return (page == null || page.size() == 0) ? 0 : page.size();
    }

}
