package com.learn.springboot.pos_project.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderSaveDTO {

    private int customer;
    private Date orderDate;
    private Double total;
    private List<RequestOrderDetailsSave> orderDetails;
}
