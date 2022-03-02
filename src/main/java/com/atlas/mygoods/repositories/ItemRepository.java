package com.atlas.mygoods.repositories;

import com.atlas.mygoods.models.Item.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT i FROM Item i WHERE i.category.id = ?1")
    List<Item> findItemByCategory(Long id, Pageable pageable);

}
