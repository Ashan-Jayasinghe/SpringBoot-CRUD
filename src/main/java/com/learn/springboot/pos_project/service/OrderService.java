package com.learn.springboot.pos_project.service;

import com.learn.springboot.pos_project.dto.request.RequestOrderSaveDTO;

public interface OrderService {
    public String addOrder(RequestOrderSaveDTO requestOrderSaveDTO);
}
