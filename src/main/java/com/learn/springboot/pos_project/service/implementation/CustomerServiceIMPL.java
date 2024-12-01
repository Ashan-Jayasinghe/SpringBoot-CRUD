package com.learn.springboot.pos_project.service.implementation;

import com.learn.springboot.pos_project.dto.CustomerDTO;
import com.learn.springboot.pos_project.dto.request.CustomerUpdateDTO;
import com.learn.springboot.pos_project.entity.Customer;
import com.learn.springboot.pos_project.exception.NotFoundException;
import com.learn.springboot.pos_project.repository.CustomerRepo;
import com.learn.springboot.pos_project.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceIMPL implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        System.out.println(customerDTO.getCustomerName());
        Customer customer = new Customer(
                customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomerSalary(),
                customerDTO.getContactNumber(),
                customerDTO.getNic(),
                customerDTO.isActive()
        );
        customerRepo.save(customer);
        return customerDTO.getCustomerName();
    }

    @Override
    public String updateCustomer(CustomerUpdateDTO customerUpdateDTO) {
        if(customerRepo.existsById(customerUpdateDTO.getCustomerId())) {
           Customer customer = customerRepo.getReferenceById(customerUpdateDTO.getCustomerId());
           customer.setCustomerName(customerUpdateDTO.getCustomerName());
           customer.setCustomerAddress(customerUpdateDTO.getCustomerAddress());
           customer.setCustomerSalary(customerUpdateDTO.getCustomerSalary());

           customerRepo.save(customer);

           return "Updated successfully";
        }else{
            throw new RuntimeException("Customer Not Found");
        }
    }

    @Override
    public CustomerDTO getCustomer(int customerId) {
        if(customerRepo.existsById(customerId)) {
            Customer customer = customerRepo.getReferenceById(customerId);
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getContactNumber(),
                    customer.getNic(),
                    customer.isActive()
            );
            return customerDTO;

        }else {
            throw new RuntimeException("Customer Not Found");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> customerList = customerRepo.findAll();
        if(customerList.size()>0){
            List<CustomerDTO> customerDTOList = new ArrayList<>();
            for(Customer customer: customerList){
                CustomerDTO customerDTO =new CustomerDTO(
                        customer.getCustomerId(),
                        customer.getCustomerName(),
                        customer.getCustomerAddress(),
                        customer.getCustomerSalary(),
                        customer.getContactNumber(),
                        customer.getNic(),
                        customer.isActive()
                );
                customerDTOList.add(customerDTO);
            }
            return customerDTOList;
        }else{
            throw new NotFoundException("Customer Not Found");
        }

    }

    @Override
    public String deleteCustomer(int customerId) {
        if(customerRepo.existsById(customerId)){
            customerRepo.deleteById(customerId);
            return "Deleted successfully: " + customerId;
        }else{
            throw new RuntimeException("Customer Not Found");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomerByActiveStatus(boolean activeState) {
        List<Customer> customerList = customerRepo.findAllByActiveEquals(activeState);
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for(Customer customer: customerList){
            CustomerDTO customerDTO =new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getContactNumber(),
                    customer.getNic(),
                    customer.isActive()
            );
            customerDTOList.add(customerDTO);
        }

        return customerDTOList;
    }


}
