package com.learn.springboot.pos_project.controller;

import com.learn.springboot.pos_project.dto.paginated.PaginatedResponseOrderDetails;
import com.learn.springboot.pos_project.dto.request.RequestOrderSaveDTO;
import com.learn.springboot.pos_project.service.OrderService;
import com.learn.springboot.pos_project.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(
            path = {"/save"}
    )
    public ResponseEntity<StandardResponse> saveItem(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO) {
        String id = orderService.addOrder(requestOrderSaveDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",id),
                HttpStatus.CREATED
        );
    }

    @GetMapping(
            path = {"/get-order-details"},
            params = {"stateType","page","size"}
    )
    public ResponseEntity<StandardResponse> getAllOrderDetails(
            @RequestParam(value ="stateType") String state,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ){
        PaginatedResponseOrderDetails paginatedResponseOrderDetails = null;
        if(state.equalsIgnoreCase("active") | state.equalsIgnoreCase("inactive")){
            boolean status = state.equalsIgnoreCase("active") ? true : false;
            paginatedResponseOrderDetails = orderService.getAllOrderDetails(status, page, size);
        }

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",paginatedResponseOrderDetails),
                HttpStatus.OK
        );
    }
}
