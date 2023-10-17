package com.atguigu.ssyx.service.impl;

import com.atguigu.ssyx.mapper.CategoryMapper;
import com.atguigu.ssyx.model.product.Category;
import com.atguigu.ssyx.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品三级分类 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-17
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
