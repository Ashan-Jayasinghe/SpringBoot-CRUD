package com.learn.springboot.pos_project.repository;

import com.learn.springboot.pos_project.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ItemRepo extends JpaRepository<Item, Integer>
{
    List<Item> findItemsByItemNameEquals(String itemName);
    List<Item> findItemsByItemNameEqualsAndActiveStatus(String itemName, boolean active);
    List<Item> findAllByActiveStatusEquals(boolean activeStatus);
    Page<Item> findAllByActiveStatusEquals(boolean activeStatus, Pageable pageable);
    int countAllByActiveStatusEquals(boolean activeStatus);
}
