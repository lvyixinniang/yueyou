package com.yueyou.domain;



import java.io.Serializable;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
* 用户
* @TableName user
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    /**
    * id
    */
    @ApiModelProperty("id")
    private Long id;
    /**
    * 用户`名称
    */
    @ApiModelProperty("用户名称")
    private String username;
    /**
    * 账号
    */
    @ApiModelProperty("账号")
    private String useraccount;
    /**
    * 用户头像
    */
    @ApiModelProperty("用户头像")
    private String avatarurl;
    /**
    * 性别
    */
    @ApiModelProperty("性别")
    private Integer gender;
    /**
    * 电话
    */
    @ApiModelProperty("电话")
    private String phone;
    /**
    * 邮箱
    */
    @ApiModelProperty("邮箱")
    private String email;
    /**
    * 标签列表
    */
    @ApiModelProperty("标签列表")
    private String tags;
    /**
    * 状态 0 -正常
    */
    @ApiModelProperty("状态 0 -正常")
    private Integer userstate;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    private Date createtime;
    /**
    * 修改时间
    */
    @ApiModelProperty("修改时间")
    private Date updatetime;
    /**
    * 是否被删除
    */
    @ApiModelProperty("是否被删除")
    private Integer isdelete;
    /**
    * 用户角色 0 - 普通用户 1 - 管理员
    */
    @ApiModelProperty("用户角色 0 - 普通用户 1 - 管理员")
    private Integer userrole;

    public User(String username, String useraccount, String avatarurl, Integer gender, String phone, String email, String tags) {
        this.username = username;
        this.useraccount = useraccount;
        this.avatarurl = avatarurl;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.tags = tags;
    }

}
