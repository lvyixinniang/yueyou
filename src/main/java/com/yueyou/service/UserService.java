package com.yueyou.service;

import com.yueyou.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yueyou.domain.VO.UserVo;

import java.util.List;

/**
* @author 喜洋洋
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-08-07 11:00:23
*/
public interface UserService extends IService<User> {

    List<UserVo> searchUserByTags(List<String> tagList);

}
