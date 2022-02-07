package com.atlas.mygoods.services;

import com.atlas.mygoods.models.Category;
import com.atlas.mygoods.models.Item;
import com.atlas.mygoods.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    public void addItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItemByCategory(Category category) {
        return itemRepository.findItemByCategory(
                category.getMainCategory(),
                category.getSubCategory());
    }
}
