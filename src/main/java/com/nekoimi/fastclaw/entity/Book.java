package com.nekoimi.fastclaw.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nekoimi.fastclaw.framework.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Book Entity
 *
 * nekoimi  2022-04-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "create")
@ApiModel(description = "书籍信息")
@TableName(value = "library.book", autoResultMap = true)
public class Book extends BaseEntity {
    private static final long serialVersionUID = 1L;

    // 书籍系列ID
    public static final String FIELD_SERIES_ID = "series_id";
    // 书籍标题
    public static final String FIELD_TITLE = "title";
    // 书籍编号
    public static final String FIELD_MOVIE_NUMBER = "movie_number";
    // 书籍作者ID
    public static final String FIELD_ACTRESS_ID = "actress_id";
    // 书籍封面图URL
    public static final String FIELD_COVER_URL = "cover_url";
    // 书籍封面图ID
    public static final String FIELD_COVER_FILE_ID = "cover_file_id";
    // 书籍时长
    public static final String FIELD_DURATION = "duration";
    // 书籍制造商
    public static final String FIELD_MANUFACTURER = "manufacturer";

    /**
     * ===================================================
     * 数据表字段
     * ===================================================
     */

    @TableField
    @ApiModelProperty(value = "书籍系列ID")
    private String seriesId;

    @TableField
    @ApiModelProperty(value = "书籍标题")
    private String title;

    @TableField
    @ApiModelProperty(value = "书籍编号")
    private String movieNumber;

    @TableField
    @ApiModelProperty(value = "书籍作者ID")
    private String actressId;

    @TableField
    @ApiModelProperty(value = "书籍封面图URL")
    private String coverUrl;

    @TableField
    @ApiModelProperty(value = "书籍封面图ID")
    private String coverFileId;

    @TableField
    @ApiModelProperty(value = "书籍时长")
    private String duration;

    @TableField
    @ApiModelProperty(value = "书籍制造商")
    private String manufacturer;

    /**
     * ===================================================
     * 非数据表字段
     * ===================================================
     */
}
