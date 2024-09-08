package com.yueyou.mapper;

import com.yueyou.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 喜洋洋
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2024-08-07 11:00:23
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {


}




