package com.goods.controller;

import com.common.vo.Response;
import com.github.pagehelper.PageInfo;
import com.goods.entity.ProductDetailVo;
import com.goods.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public Response<PageInfo> list(@RequestParam(required = false) Integer categoryId,
                                     @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return productService.list(categoryId, pageNum, pageSize);
    }

    @GetMapping("/products/{productId}")
    public Response<ProductDetailVo> detail(@PathVariable Integer productId) {
        return productService.detail(productId);
    }
}
