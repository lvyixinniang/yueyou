package com.yueyou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yueyou.domain.Tag;
import com.yueyou.service.TagService;
import com.yueyou.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author 喜洋洋
* @description 针对表【tag(标签)】的数据库操作Service实现
* @createDate 2024-08-07 11:00:06
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




