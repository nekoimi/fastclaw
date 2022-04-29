package com.nekoimi.vasashi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nekoimi.vasashi.framework.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Magnet Entity
 *
 * nekoimi  2022-04-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "create")
@ApiModel(description = "书籍磁力链接")
@TableName(value = "library.book_magnet", autoResultMap = true)
public class Magnet extends BaseEntity {
    private static final long serialVersionUID = 1L;

    // 书籍ID
    public static final String FIELD_BOOK_ID = "book_id";
    // 磁力链接
    public static final String FIELD_LINK = "link";

    /**
     * ===================================================
     * 数据表字段
     * ===================================================
     */

    @TableField
    @ApiModelProperty(value = "书籍ID")
    private String bookId;

    @TableField
    @ApiModelProperty(value = "磁力链接")
    private String link;

    /**
     * ===================================================
     * 非数据表字段
     * ===================================================
     */
}
