package com.atlas.mygoods.services;


import com.atlas.mygoods.models.Item.Category.Category;
import com.atlas.mygoods.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(
            CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(String mainCat, String subCat) {
        final Category category = new Category(mainCat, subCat);
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public int getCount() {
        return categoryRepository.something();
    }
}
