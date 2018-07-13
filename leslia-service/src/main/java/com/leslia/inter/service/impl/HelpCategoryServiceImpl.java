package com.leslia.inter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leslia.inter.api.HelpCategoryService;
import com.leslia.inter.mapper.HelpCategoryMapper;
import com.leslia.inter.pojo.HelpCategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
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
