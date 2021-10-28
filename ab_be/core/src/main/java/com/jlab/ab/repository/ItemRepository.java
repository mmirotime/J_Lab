package com.jlab.ab.repository;

import com.jlab.ab.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT p FROM Item p ORDER BY p.id DESC")
    List<Item> findAllDesc();
}
