package com.cloud.platform.common.request;

import com.cloud.platform.common.query.BaseQuery;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: ZhouShuai
 * @Date: 2021-06-27 16:27
 */
@Data
@NoArgsConstructor
public class PageQueryRequest<T> extends BaseQuery<T> {
    private int pageIndex = 1;
    private int pageSize = 20;
    private boolean queryCount = true;
    private int start;
}
