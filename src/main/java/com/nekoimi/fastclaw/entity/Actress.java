package com.nekoimi.fastclaw.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nekoimi.fastclaw.framework.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Actress Entity
 *
 * nekoimi  2022-04-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "create")
@ApiModel(description = "书籍作者信息")
@TableName(value = "library.book_actress", autoResultMap = true)
public class Actress extends BaseEntity {
    private static final long serialVersionUID = 1L;

    // 书籍作者名称
    public static final String FIELD_NAME = "name";
    // 书籍作者封面
    public static final String FIELD_COVER = "cover";

    /**
     * ===================================================
     * 数据表字段
     * ===================================================
     */

    @TableField
    @ApiModelProperty(value = "书籍作者名称")
    private String name;

    @TableField
    @ApiModelProperty(value = "书籍作者封面")
    private String cover;

    /**
     * ===================================================
     * 非数据表字段
     * ===================================================
     */
}
