package com.nekoimi.fastclaw.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nekoimi.fastclaw.framework.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Image Entity
 *
 * nekoimi  2022-04-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "create")
@ApiModel(description = "书籍图片")
@TableName(value = "library.book_image", autoResultMap = true)
public class Image extends BaseEntity {
    private static final long serialVersionUID = 1L;

    // 书籍ID
    public static final String FIELD_BOOK_ID = "book_id";
    // 原始地址
    public static final String FIELD_URL = "url";
    // 文件ID
    public static final String FIELD_FILE_ID = "file_id";

    /**
     * ===================================================
     * 数据表字段
     * ===================================================
     */

    @TableField
    @ApiModelProperty(value = "书籍ID")
    private String bookId;

    @TableField
    @ApiModelProperty(value = "原始地址")
    private String url;

    @TableField
    @ApiModelProperty(value = "文件ID")
    private String fileId;

    /**
     * ===================================================
     * 非数据表字段
     * ===================================================
     */
}
