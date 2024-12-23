package com.learn.springboot.pos_project.service.implementation;

import com.learn.springboot.pos_project.dto.paginated.PaginatedResponseOrderDetails;
import com.learn.springboot.pos_project.dto.queryInterface.OrderDetailInterface;
import com.learn.springboot.pos_project.dto.request.RequestOrderSaveDTO;
import com.learn.springboot.pos_project.dto.response.ResponseOrderDetailsDTO;
import com.learn.springboot.pos_project.entity.OrderDetails;
import com.learn.springboot.pos_project.entity.Orders;
import com.learn.springboot.pos_project.repository.CustomerRepo;
import com.learn.springboot.pos_project.repository.ItemRepo;
import com.learn.springboot.pos_project.repository.OrderDetailsRepo;
import com.learn.springboot.pos_project.repository.OrderRepo;
import com.learn.springboot.pos_project.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Autowired
    private ItemRepo itemRepo;
    @Override
    @Transactional
    public String addOrder(RequestOrderSaveDTO requestOrderSaveDTO) {
        Orders order = new Orders(
                customerRepo.getReferenceById(requestOrderSaveDTO.getCustomer()),
                requestOrderSaveDTO.getOrderDate(),
                requestOrderSaveDTO.getTotal()
        );
        orderRepo.save(order);
        if(orderRepo.existsById(order.getOrderId())) {
            List<OrderDetails> orderDetails =  modelMapper.map(requestOrderSaveDTO.getOrderDetails(), new TypeToken<List<OrderDetails>>() {}.getType());
            for(int i=0; i<orderDetails.size(); i++) {
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItems(itemRepo.getReferenceById(requestOrderSaveDTO.getOrderDetails().get(i).getItems()));
            }
            if(orderDetails.size() > 0) {
                orderDetailsRepo.saveAll(orderDetails);
            }
            return "success";
        }
        return "fail";

    }

    @Override
    public PaginatedResponseOrderDetails getAllOrderDetails(boolean state, int page, int size) {
        List<OrderDetailInterface> orderDetails = orderRepo.getAllOrderDetails(state, PageRequest.of(page,size));
        if (!orderDetails.isEmpty()) {
            //System.out.println("come " + orderDetails.get(0).getCustomerName());
            List<ResponseOrderDetailsDTO> list = new ArrayList<>();
            for(OrderDetailInterface orderDetail : orderDetails) {
                ResponseOrderDetailsDTO responseOrderDetailsDTO = new ResponseOrderDetailsDTO(
                        orderDetail.getCustomerName(),
                        orderDetail.getCustomerAddress(),
                        orderDetail.getContactNumber(),
                        orderDetail.getOrderDate(),
                        orderDetail.getTotal()
                );
                list.add(responseOrderDetailsDTO);
            }
            PaginatedResponseOrderDetails paginatedResponseOrderDetails = new PaginatedResponseOrderDetails(
                    list,
                    orderRepo.countAllOrders(state)
            );
            return paginatedResponseOrderDetails;
        } else {
            throw new EmptyResultDataAccessException(1);
        }

    }
}
