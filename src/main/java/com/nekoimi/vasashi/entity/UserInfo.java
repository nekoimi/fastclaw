package com.nekoimi.vasashi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nekoimi.vasashi.framework.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * UserInfo Entity
 *
 * nekoimi  2022-04-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "create")
@ApiModel(description = "用户信息")
@TableName(value = "library.sys_user_info", autoResultMap = true)
public class UserInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    // 账号ID
    public static final String FIELD_USER_ID = "user_id";
    // 昵称
    public static final String FIELD_NICKNAME = "nickname";
    // 头像
    public static final String FIELD_AVATAR = "avatar";

    /**
     * ===================================================
     * 数据表字段
     * ===================================================
     */

    @TableField
    @ApiModelProperty(value = "账号ID")
    private String userId;

    @TableField
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @TableField
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * ===================================================
     * 非数据表字段
     * ===================================================
     */
}
