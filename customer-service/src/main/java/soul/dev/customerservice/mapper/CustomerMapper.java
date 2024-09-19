package soul.dev.customerservice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import soul.dev.customerservice.Dtos.CustomerDto;
import soul.dev.customerservice.entities.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerMapper {
    private ModelMapper modelMapper = new ModelMapper();
    public CustomerDto fromCustomer(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }
    public Customer fromCustomerDto(CustomerDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }
    public List<CustomerDto> fromCustomers(List<Customer> customers) {
           return customers.stream().map(c -> modelMapper.map(c, CustomerDto.class)).collect(Collectors.toList()) ;
    }
}
