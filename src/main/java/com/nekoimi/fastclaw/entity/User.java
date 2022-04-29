package com.nekoimi.fastclaw.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nekoimi.fastclaw.framework.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * User Entity
 *
 * nekoimi  2022-04-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "create")
@ApiModel(description = "账号信息")
@TableName(value = "library.sys_user", autoResultMap = true)
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    // 账号
    public static final String FIELD_USERNAME = "username";
    // 密码
    public static final String FIELD_PASSWORD = "password";
    // 是否被禁用：0 - 禁用，1 - 正常
    public static final String FIELD_ENABLE = "enable";

    /**
     * ===================================================
     * 数据表字段
     * ===================================================
     */

    @TableField
    @ApiModelProperty(value = "账号")
    private String username;

    @TableField
    @ApiModelProperty(value = "密码")
    private String password;

    @TableField
    @ApiModelProperty(value = "是否被禁用：0 - 禁用，1 - 正常")
    private Integer enable;

    /**
     * ===================================================
     * 非数据表字段
     * ===================================================
     */
}
