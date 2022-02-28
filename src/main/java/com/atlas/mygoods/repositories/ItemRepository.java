package com.atlas.mygoods.repositories;

import com.atlas.mygoods.models.Item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

//    @Query(value = "SELECT i FROM Item i WHERE i.mainCategory = ?1 and i.subCategory = ?2")
//    List<Item> findItemByCategory(String mainCategory, String subCategory);

}
