package com.learn.springboot.pos_project.service;

import com.learn.springboot.pos_project.dto.paginated.PaginatedResponseOrderDetails;
import com.learn.springboot.pos_project.dto.request.RequestOrderSaveDTO;

public interface OrderService {
    public String addOrder(RequestOrderSaveDTO requestOrderSaveDTO);

    public PaginatedResponseOrderDetails getAllOrderDetails(boolean state, int page, int size);
}
