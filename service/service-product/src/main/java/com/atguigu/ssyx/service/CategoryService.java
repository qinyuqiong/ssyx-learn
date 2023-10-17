package com.atguigu.ssyx.service;


import com.atguigu.ssyx.model.product.Category;
import com.atguigu.ssyx.vo.product.CategoryQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品三级分类 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-17
 */
public interface CategoryService extends IService<Category> {

    IPage<Category> selectPage(Page<Category> categoryPage, CategoryQueryVo categoryQueryVo);
}
