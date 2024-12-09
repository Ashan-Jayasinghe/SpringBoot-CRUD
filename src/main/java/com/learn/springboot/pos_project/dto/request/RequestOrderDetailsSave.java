package com.learn.springboot.pos_project.dto.request;

import com.learn.springboot.pos_project.entity.Item;
import com.learn.springboot.pos_project.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderDetailsSave {

    private String itemName;
    private double qty;
    private Double amount;
    private int orders;
    private int items;

}
