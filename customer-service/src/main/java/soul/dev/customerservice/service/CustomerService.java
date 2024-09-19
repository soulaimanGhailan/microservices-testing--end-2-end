package soul.dev.customerservice.service;

import soul.dev.customerservice.Dtos.CustomerDto;
import soul.dev.customerservice.exceptions.EmailExistedException;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto) throws EmailExistedException;
}
