package com.atlas.mygoods.services;

import com.atlas.mygoods.models.Image;
import com.atlas.mygoods.models.Item.Category.Category;
import com.atlas.mygoods.models.Item.Item;
import com.atlas.mygoods.repositories.CategoryRepository;
import com.atlas.mygoods.repositories.ImageRepository;
import com.atlas.mygoods.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    private final ImageService imageService;

    @Autowired
    public ItemService(ItemRepository itemRepository, ImageRepository imageRepository, CategoryRepository categoryRepository, ImageService imageService) {
        this.itemRepository = itemRepository;
        this.imageRepository = imageRepository;
        this.categoryRepository = categoryRepository;
        this.imageService = imageService;
    }


    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    @Transactional
    public Item addItem(Item item) {
        item.setDate(new Date());

        Optional<Category> category = categoryRepository.findById(item.getCategory().getId());

        System.out.println("Category: " + category.toString());

        if (category.isPresent()) {
            final Category updatedCategory = category.get();

//            Set Category to newly add item
            item.setCategory(updatedCategory);

//            Update current category with a new item to the list
            final List<Item> updateItems = category.get().getItems();
            updateItems.add(item);
            updatedCategory.setItems(updateItems);

            return itemRepository.save(item);

        }
        return null;
    }

    public void addItem(Item item, List<MultipartFile> files) throws IOException {
        final List<Image> images = new ArrayList<>();
        if (files == null || files.size() == 0) {
            return;
        }
        for (MultipartFile file : files) {
            if (file == null) {
                return;
            }
            if (file.getOriginalFilename() == null) {
                return;
            }
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String imageBase64 = Base64.getEncoder().encodeToString(file.getBytes());
            final Image image = new Image(imageBase64, fileName);
            images.add(image);
        }
        imageRepository.saveAll(images);
        item.setDate(new Date());
        item.setImages(images);
        itemRepository.save(item);
    }

    public List<Item> getItemByCategoryWithPaginationAndSort(
            int page,
            String sortBy,
            Long catId
    ) {
        final Pageable pageable = PageRequest.of(page, 10, Sort.by(sortBy).descending());
        return itemRepository.findItemByCategory(catId, pageable);
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
        item.setCategory(newItem.getCategory());
//        item.setSubCategory(newItem.getSubCategory());
//        item.setMainCategory(newItem.getMainCategory());
        item.setDescription(newItem.getDescription());
        item.setPhone(newItem.getPhone());
        item.setImages(newItem.getImages());
        item.setAmount(newItem.getAmount());
        item.setPrice(newItem.getPrice());
    }

}
