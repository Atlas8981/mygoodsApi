package com.atlas.mygoods.services;


import com.atlas.mygoods.models.Item.Category.MainCategory;
import com.atlas.mygoods.repositories.MainCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final MainCategoryRepository mainCategoryRepository;

    @Autowired
    public CategoryService(
            MainCategoryRepository mainCategoryRepository) {
        this.mainCategoryRepository = mainCategoryRepository;
    }

    public void addMainCategory(String mainCat) {
        final MainCategory mainCategory = new MainCategory(mainCat);
        mainCategoryRepository.save(mainCategory);
    }

    @Transactional
    public void addSubCategory(Long id, String subCat) {
        final Optional<MainCategory> tempMainCat = mainCategoryRepository.findById(id);
        if (tempMainCat.isPresent()) {
            final MainCategory mainCat = tempMainCat.get();
            final List<String> tempListOfSubCat = mainCat.getSubCategories();
            tempListOfSubCat.add(subCat);
            mainCat.setSubCategories(tempListOfSubCat);
        }
    }

    public void addSubCategories(List<String> subCats) {

    }
}
