package com.learn.springboot.pos_project.dto.response;

import com.learn.springboot.pos_project.dto.request.RequestOrderDetailsSave;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseOrderDetailsDTO {
    //customer
    private String customerName;
    private String customerAddress;
    private ArrayList contactNumber;

    //order
    private Date orderDate;
    private Double total;

}
