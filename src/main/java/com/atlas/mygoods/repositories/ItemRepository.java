package com.atlas.mygoods.repositories;

import com.atlas.mygoods.entities.ItemEntity;
import com.atlas.mygoods.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {

}
