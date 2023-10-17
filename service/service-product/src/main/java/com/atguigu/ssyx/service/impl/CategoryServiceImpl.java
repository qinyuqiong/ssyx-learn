package com.atguigu.ssyx.service.impl;

import com.atguigu.ssyx.mapper.CategoryMapper;
import com.atguigu.ssyx.model.product.Category;
import com.atguigu.ssyx.service.CategoryService;
import com.atguigu.ssyx.vo.product.CategoryQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public IPage<Category> selectPage(Page<Category> categoryPage, CategoryQueryVo categoryQueryVo) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        String name = categoryQueryVo.getName();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like(Category::getName, name);
        }
        return baseMapper.selectPage(categoryPage, queryWrapper);
    }
}
