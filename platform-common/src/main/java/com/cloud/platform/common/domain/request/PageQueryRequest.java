package com.cloud.platform.common.domain.request;

import com.cloud.platform.common.domain.query.BaseQuery;
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
    private Integer pageIndex = 1;
    private Integer pageSize = 20;
    private boolean queryCount = true;
    private Integer start;
}
