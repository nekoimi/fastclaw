package com.nekoimi.vasashi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nekoimi.vasashi.framework.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * LabelRel Entity
 *
 * nekoimi  2022-04-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "create")
@ApiModel(description = "书籍标签Rel")
@TableName(value = "library.book_label_rel", autoResultMap = true)
public class LabelRel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    // 书籍ID
    public static final String FIELD_BOOK_ID = "book_id";
    // 标签ID
    public static final String FIELD_LABEL_ID = "label_id";

    /**
     * ===================================================
     * 数据表字段
     * ===================================================
     */

    @TableField
    @ApiModelProperty(value = "书籍ID")
    private String bookId;

    @TableField
    @ApiModelProperty(value = "标签ID")
    private String labelId;

    /**
     * ===================================================
     * 非数据表字段
     * ===================================================
     */
}
