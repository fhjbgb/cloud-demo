package com.goods.controller;


import com.common.vo.Response;
import com.goods.entity.Category;
import com.goods.entity.CategoryVo;
import com.goods.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("categorylist")
    public Response getCategoryAllList(){
        List<CategoryVo> res= categoryService.getCategoryALLList();
        return new Response(res);
    }
}
