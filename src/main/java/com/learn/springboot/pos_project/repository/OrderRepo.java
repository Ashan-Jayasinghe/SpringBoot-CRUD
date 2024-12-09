package com.learn.springboot.pos_project.repository;

import com.learn.springboot.pos_project.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrderRepo extends JpaRepository<Orders, Integer> {
}
