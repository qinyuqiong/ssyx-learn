package com.atguigu.ssyx.controller;


import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.product.Category;
import com.atguigu.ssyx.service.CategoryService;
import com.atguigu.ssyx.vo.product.CategoryQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品三级分类 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-17
 */

@RestController
@RequestMapping("/admin/product/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("商品分类列表")
    @GetMapping("/{page}/{limit}")
    public Result<IPage<Category>> list(@PathVariable Long page,
                                        @PathVariable Long limit,
                                        CategoryQueryVo categoryQueryVo) {
        Page<Category> categoryPage = new Page<>(page, limit);
        IPage<Category> pageModel = categoryService.selectPage(categoryPage, categoryQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation("根据id查找信息")
    @GetMapping("/get/{id}")
    public Result<Category> getById(@PathVariable Long id) {
        Category byId = categoryService.getById(id);
        return Result.ok(byId);
    }

    @ApiOperation("添加商品分类")
    @PostMapping("/save")
    public Result<Void> save(@RequestBody Category category) {
        categoryService.save(category);
        return Result.ok(null);
    }

    @ApiOperation("根据id更新")
    @PutMapping("/update")
    public Result<Void> updateById(@RequestBody Category category) {
        categoryService.updateById(category);
        return Result.ok(null);
    }

    @ApiOperation("根据id删除")
    @DeleteMapping("/remove/{id}")
    public Result<Void> removeById(@PathVariable Long id) {
        categoryService.removeById(id);
        return Result.ok(null);
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batchRemove")
    public Result<Void> removeRows(@RequestBody List<Long> idList) {
        categoryService.removeByIds(idList);
        return Result.ok(null);
    }

    @ApiOperation("获取所有")
    @GetMapping("/findAllList")
    public Result<List<Category>> findAllList() {
        List<Category> list = categoryService.list();
        return Result.ok(list);
    }
}

