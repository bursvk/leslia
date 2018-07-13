package com.leslia.test.inter;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.leslia.inter.api.HelpCategoryService;
import com.leslia.inter.pojo.HelpCategory;
import com.leslia.test.base.BaseTestDubbo;
import org.junit.Test;

public class HelpCategoryTest extends BaseTestDubbo {

    @Reference
    private HelpCategoryService helpCategoryService;

    @Test
    public void getList(){
        Object result=helpCategoryService.getList();
        PageInfo<HelpCategory> pageInfo= (PageInfo<HelpCategory>)result;
        System.out.println(pageInfo.getList().size());
        System.out.println(pageInfo.getTotal());
    }

}
