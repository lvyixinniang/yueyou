package com.yueyou.domain;


import java.io.Serializable;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
* 标签
* @TableName tag
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag implements Serializable {

    /**
    * id
    */
    @ApiModelProperty("id")
    private Long id;
    /**
    * 标签名称
    */
    @ApiModelProperty("标签名称")
    private String tagname;
    /**
    * 用户 id
    */
    @ApiModelProperty("用户 id")
    private Long userid;
    /**
    * 父标签 Id
    */
    @ApiModelProperty("父标签 Id")
    private Long parentid;
    /**
    * 是否为父标签 0 - 不是 , 1 - 父标签
    */
    @ApiModelProperty("是否为父标签 0 - 不是 , 1 - 父标签")
    private Integer isparent;
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
    @TableLogic
    private Integer isdelete;



}
