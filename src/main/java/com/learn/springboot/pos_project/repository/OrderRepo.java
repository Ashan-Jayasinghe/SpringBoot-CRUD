package com.learn.springboot.pos_project.repository;

import com.learn.springboot.pos_project.dto.queryInterface.OrderDetailInterface;
import com.learn.springboot.pos_project.dto.response.ResponseOrderDetailsDTO;
import com.learn.springboot.pos_project.entity.Orders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface OrderRepo extends JpaRepository<Orders, Integer> {


//    @Query(value = "select c.customer_name as customerName, c.customer_address as customerAddress, c.contact_numbers as contactNumber, o.order_date as orderDate , o.total as total from customer c, orders o where o.active_state = ?1 and o.customer_id = c.customer_id ",nativeQuery = true)
//    List<OrderDetailInterface> getAllOrderDetails(boolean state, Pageable pageable);

    @Query(value = "SELECT c.customer_name AS customerName, " +
            "c.customer_address AS customerAddress, " +
            "c.contact_numbers AS contactNumber, " +
            "o.order_date AS orderDate, " +
            "o.total AS total " +
            "FROM customer c, orders o " +
            "WHERE o.active_state = ?1 " +
            "AND o.customer_id = c.customer_id",
            nativeQuery = true)
    List<OrderDetailInterface> getAllOrderDetails(boolean state, Pageable pageable);

    @Query(value ="select count(*) from customer c, orders o where o.active_state = ?1 and o.customer_id = c.customer_id",nativeQuery = true)
    long countAllOrders(boolean state);
}
