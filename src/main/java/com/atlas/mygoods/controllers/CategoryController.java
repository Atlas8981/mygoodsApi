package com.atlas.mygoods.controllers;

import com.atlas.mygoods.models.Item.Category.Category;
import com.atlas.mygoods.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public Category saveCategory(@RequestParam("mainCat") String mainCat, @RequestParam("subCat") String subCat) {
        return categoryService.addCategory(mainCat, subCat);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping(path = "/count")
    public int getCount() {
        System.out.println("categoryService.getCount()" + categoryService.getCount());
        return categoryService.getCount();
    }
}
