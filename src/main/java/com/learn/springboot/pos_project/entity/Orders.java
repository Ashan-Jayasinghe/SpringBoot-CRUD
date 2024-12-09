package com.learn.springboot.pos_project.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table
@TypeDefs({
        @TypeDef(name = "json",typeClass = JsonType.class)
})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orders {
    @Id
    @Column(name = "order_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "order_date", columnDefinition = "DATETIME")
    private Date orderDate;

    @Column(name = "total",nullable = false)
    private Double total;

    @OneToMany(mappedBy = "orders")
    private Set<OrderDetails> orderDetails;


    public Orders(Customer customer, Date orderDate, double total) {
        this.customer = customer;
        this.orderDate = orderDate;
        this.total = total;
    }
}
