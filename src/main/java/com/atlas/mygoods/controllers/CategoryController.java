package com.atlas.mygoods.controllers;

import com.atlas.mygoods.models.Item.Category.CategoryRequest;
import com.atlas.mygoods.models.Item.Category.MainCategory;
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

    @PostMapping(path = "/mainCat")
    public void saveMainCategory(@RequestParam("mainCat") String mainCat) {
        categoryService.addMainCategory(mainCat);
    }

    @PostMapping(path = "/subCat")
    public void addMainCategory(@RequestParam("id") String id, @RequestParam("subCat") String subCat) {
        categoryService.addSubCategory(Long.parseLong(id), subCat);

    }

    @GetMapping(path = "/all")
    public List<MainCategory> getAllCategories() {
        return null;
    }

}
