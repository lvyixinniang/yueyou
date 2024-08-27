package com.yueyou.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ExcelImportANDExportDto {

    /**
     * 用户`名称
     */
    @ExcelProperty("姓名")
    private String username;
    /**
     * 账号
     */
    @ExcelProperty("账号")
    private String useraccount;
    /**
     * 用户头像
     */
    @ExcelProperty("用户头像")
    private String avatarurl;
    /**
     * 性别
     */
    @ExcelProperty("性别")
    private Integer gender;
    /**
     * 电话
     */
    @ExcelProperty("电话")
    private String phone;
    /**
     * 邮箱
     */
    @ExcelProperty("邮箱")
    private String email;
    /**
     * 标签列表
     */
    @ExcelProperty("标签列表")
    private String tags;

}