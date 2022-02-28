package com.atlas.mygoods.services;

import com.atlas.mygoods.models.Item.Item;
import com.atlas.mygoods.repositories.ImageRepository;
import com.atlas.mygoods.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ImageRepository imageRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, ImageRepository imageRepository) {
        this.itemRepository = itemRepository;
        this.imageRepository = imageRepository;
    }

    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    public void addItem(Item item) {
        itemRepository.save(item);
    }

//    public List<Item> findItemByCategory(Category category) {
//        return itemRepository.findItemByCategory(
//                category.getMainCategory(),
//                category.getSubCategory());
//    }

    public void deleteItemById(Long id) {
        boolean exist = itemRepository.existsById(id);
        if (!exist) {
            throw new IllegalStateException("Item with id " + id + " does not exist");
        }
        itemRepository.deleteById(id);
    }

    @Transactional
    public void updateItemByItem(Long id, Item newItem) {
        final Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Item with id " + newItem.getItemid() + " does not exist")
                );
        item.setName(newItem.getName());
        item.setAddress(newItem.getAddress());
        item.setSubCategory(newItem.getSubCategory());
        item.setMainCategory(newItem.getMainCategory());
        item.setDescription(newItem.getDescription());
        item.setPhone(newItem.getPhone());
        item.setImages(newItem.getImages());
        item.setAmount(newItem.getAmount());
        item.setPrice(newItem.getPrice());
    }

}
