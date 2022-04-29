package com.nekoimi.vasashi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nekoimi.vasashi.framework.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Series Entity
 *
 * nekoimi  2022-04-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "create")
@ApiModel(description = "书籍系列信息")
@TableName(value = "library.book_series", autoResultMap = true)
public class Series extends BaseEntity {
    private static final long serialVersionUID = 1L;

    // 书籍系列名称
    public static final String FIELD_TITLE = "title";

    /**
     * ===================================================
     * 数据表字段
     * ===================================================
     */

    @TableField
    @ApiModelProperty(value = "书籍系列名称")
    private String title;

    /**
     * ===================================================
     * 非数据表字段
     * ===================================================
     */
}
