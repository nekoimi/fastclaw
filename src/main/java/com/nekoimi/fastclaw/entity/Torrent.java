package com.nekoimi.fastclaw.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nekoimi.fastclaw.framework.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Torrent Entity
 *
 * nekoimi  2022-04-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "create")
@ApiModel(description = "书籍种子文件")
@TableName(value = "library.book_torrent", autoResultMap = true)
public class Torrent extends BaseEntity {
    private static final long serialVersionUID = 1L;

    // 书籍ID
    public static final String FIELD_BOOK_ID = "book_id";
    // 种子文件链接
    public static final String FIELD_URL = "url";
    // 种子文件ID
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
    @ApiModelProperty(value = "种子文件链接")
    private String url;

    @TableField
    @ApiModelProperty(value = "种子文件ID")
    private String fileId;

    /**
     * ===================================================
     * 非数据表字段
     * ===================================================
     */
}
