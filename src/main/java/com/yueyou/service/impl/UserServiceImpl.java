package com.yueyou.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yueyou.domain.User;
import com.yueyou.domain.VO.UserVo;
import com.yueyou.service.UserService;
import com.yueyou.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
* @author 喜洋洋
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2024-08-07 11:00:23
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserVo> searchUserByTags(List<String> tagList) {
        if (CollectionUtils.isEmpty(tagList)){
            return null;
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        for (String tagName : tagList) {
//            queryWrapper = queryWrapper.like("tags",tagName);
//        }
        List<User> users = userMapper.selectList(queryWrapper);
        Gson gson = new Gson();
        return users.stream().filter(user -> { String tagStr = user.getTags();

            if (StringUtils.isEmpty(tagStr)) return false;
//            反序列化 为 json
            Set<String> tempTagNameSet = gson.fromJson(tagStr,new TypeToken<Set<String>>(){}.getType());
            tempTagNameSet = Optional.ofNullable(tempTagNameSet).orElse(new HashSet<>());
//            gson.toJson(tempTagNameSet); // 序列化
            for (String tagname : tempTagNameSet) {
                if (!tempTagNameSet.contains(tagname)){
                    return false;
                }
            }
            return true;}).map(user -> BeanUtil.copyProperties(user, UserVo.class)).collect(Collectors.toList());


//        return users.stream().map(user -> BeanUtil.copyProperties(user, UserVo.class)).collect(Collectors.toList());
    }
}




