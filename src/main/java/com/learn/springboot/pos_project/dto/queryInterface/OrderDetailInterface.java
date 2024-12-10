package com.learn.springboot.pos_project.dto.queryInterface;

import java.util.ArrayList;
import java.util.Date;

public interface OrderDetailInterface {

    String getCustomerName();
    String getCustomerAddress();
    ArrayList getContactNumber();
    Date getOrderDate();
    Double getTotal();
}
