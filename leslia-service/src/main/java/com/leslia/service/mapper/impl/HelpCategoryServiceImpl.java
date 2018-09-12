package com.leslia.service.mapper.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leslia.api.api.HelpCategoryService;
import com.leslia.api.pojo.HelpCategory;
import com.leslia.service.mapper.HelpCategoryMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Transactional
public class HelpCategoryServiceImpl implements HelpCategoryService{

    @Resource
    private HelpCategoryMapper helpCategoryMapper;


    @Override
    public PageInfo<HelpCategory> getList() {
        PageHelper.startPage(1,10);
        List<HelpCategory> list= helpCategoryMapper.getList();
        PageInfo<HelpCategory> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }



}
