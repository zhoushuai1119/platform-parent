package com.cloud.platform.common.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/5/7 14:47
 * @version: v1
 */
@Data
public class BaseEntity implements Entity, Serializable {

    private static final long serialVersionUID = -3212190033553111442L;

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 更新时间
     */
    private LocalDateTime gmtModify;
    /**
     * 是否删除（0：正常，1：删除）
     */
    private Integer isDeleted;
}
