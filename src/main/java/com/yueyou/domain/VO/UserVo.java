package com.yueyou.domain.VO;


import lombok.Data;

import java.util.Date;

@Data
public class UserVo {
    /**
     * id
     */
    private Long id;
    /**
     * 用户`名称
     */
    private String username;
    /**
     * 账号
     */
    private String useraccount;
    /**
     * 用户头像
     */
    private String avatarurl;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 标签列表
     */
    private String tags;
    /**
     * 状态 0 -正常
     */
    private Integer userstate;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 修改时间
     */
    private Date updatetime;
    /**
     * 是否被删除
     */
    private Integer isdelete;
    /**
     * 用户角色 0 - 普通用户 1 - 管理员
     */
    private Integer userrole;
}
