package com.goods.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.common.vo.Response;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goods.dao.ProductMapper;
import com.goods.entity.Product;
import com.goods.entity.ProductDetailVo;
import com.goods.entity.ProductVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ProductService {

    final int ON_SALE=1;

    final int OFF_SALE=2;

    final int DELETE=3;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryService categoryService;

    public Response<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize) {
        Set<Integer> categoryIdSet = new HashSet<>();
        if (categoryId != null) {
            categoryService.findSubCategoryId(categoryId, categoryIdSet);
            categoryIdSet.add(categoryId);
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Product> productList = productMapper.selectByCategoryIdSet(categoryIdSet);
        List<ProductVo> productVoList = productList.stream()
                .map(e -> {
                    ProductVo productVo = new ProductVo();
                    BeanUtils.copyProperties(e, productVo);
                    return productVo;
                })
                .collect(Collectors.toList());

        PageInfo pageInfo = new PageInfo<>(productList);
        pageInfo.setList(productVoList);
        return  new Response(pageInfo);
    }

    public Response<ProductDetailVo> detail(Integer productId) {
        Product product = productMapper.selectByPrimaryKey(productId);

        //只对确定性条件判断
        if (product.getStatus().equals(OFF_SALE)
                || product.getStatus().equals(DELETE)) {
             return new Response(404,"PRODUCT_OFF_SALE_OR_DELETE");
        }

        ProductDetailVo productDetailVo = new ProductDetailVo();
        BeanUtils.copyProperties(product, productDetailVo);
        //敏感数据处理
        productDetailVo.setStock(product.getStock() > 100 ? 100 : product.getStock());
        return new Response(productDetailVo);
    }

}
