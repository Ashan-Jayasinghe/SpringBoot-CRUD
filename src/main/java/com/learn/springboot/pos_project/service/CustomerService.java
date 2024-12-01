package com.learn.springboot.pos_project.service;

import com.learn.springboot.pos_project.dto.CustomerDTO;
import com.learn.springboot.pos_project.dto.request.CustomerUpdateDTO;

import java.util.List;

public interface CustomerService {
    public String saveCustomer(CustomerDTO customerDTO);

    public String updateCustomer(CustomerUpdateDTO customerUpdateDTO);

    public CustomerDTO getCustomer(int customerId);


    public List<CustomerDTO> getAllCustomer();

    public String deleteCustomer(int customerId);

    public List<CustomerDTO> getAllCustomerByActiveStatus(boolean activeState);
}
