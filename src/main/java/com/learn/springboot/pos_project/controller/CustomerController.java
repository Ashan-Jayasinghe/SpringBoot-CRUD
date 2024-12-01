package com.learn.springboot.pos_project.controller;

import com.learn.springboot.pos_project.dto.CustomerDTO;
import com.learn.springboot.pos_project.dto.request.CustomerUpdateDTO;
import com.learn.springboot.pos_project.entity.Customer;
import com.learn.springboot.pos_project.service.CustomerService;
import com.learn.springboot.pos_project.service.implementation.CustomerServiceIMPL;
import com.learn.springboot.pos_project.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public String saveCustomer(@RequestBody CustomerDTO customerDTO){

//        if we followed this approach, memory heap will be full with the time. due to we have to made the connection to the particular service several times.
//        CustomerServiceIMPL customerServiceIMPL = new CustomerServiceIMPL();
//        customerServiceIMPL.saveCustomer(customerDTO);
//        String message = customerDTO.toString();
//        System.out.println(message);
        String message = customerService.saveCustomer(customerDTO);
        return message;
    }

    @PutMapping("/update")
    public String updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO){
        String message = customerService.updateCustomer(customerUpdateDTO);
        return message;
    }

    @GetMapping(
            path = "/get-by-id",
            params = "id"
    )
    public  CustomerDTO  getCustomerById(@RequestParam(value = "id") int customerId){
        CustomerDTO customerDetails = customerService.getCustomer(customerId);
        return customerDetails;
    }

    @GetMapping(
            path = "/get-all-customers"
    )
    public ResponseEntity<StandardResponse> getAllCustomers(){
        List<CustomerDTO> customerList = customerService.getAllCustomer();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",customerList),
                HttpStatus.OK
        );
    }

    @DeleteMapping(
            path = "/delete-customer/{id}"
    )
    public String deleteCustomer(@PathVariable(value = "id") int customerId){
        String deletedMsg = customerService.deleteCustomer(customerId);
        return deletedMsg;

    }

    @GetMapping(
            path = "/get-customers-by-active-state/{status}"
    )
    public List<CustomerDTO>getAllCustomersByActiveState(@PathVariable(value = "status") boolean activeState){
        List<CustomerDTO> customerList = customerService.getAllCustomerByActiveStatus(activeState);
        return customerList;
    }


}
