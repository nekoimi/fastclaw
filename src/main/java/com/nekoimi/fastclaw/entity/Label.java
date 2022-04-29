package com.nekoimi.fastclaw.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nekoimi.fastclaw.framework.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Label Entity
 *
 * nekoimi  2022-04-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "create")
@ApiModel(description = "书籍标签信息")
@TableName(value = "library.book_label", autoResultMap = true)
public class Label extends BaseEntity {
    private static final long serialVersionUID = 1L;

    // 书籍标签名称
    public static final String FIELD_NAME = "name";

    /**
     * ===================================================
     * 数据表字段
     * ===================================================
     */

    @TableField
    @ApiModelProperty(value = "书籍标签名称")
    private String name;

    /**
     * ===================================================
     * 非数据表字段
     * ===================================================
     */
}
