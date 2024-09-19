package soul.dev.customerservice.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soul.dev.customerservice.Dtos.CustomerDto;
import soul.dev.customerservice.entities.Customer;
import soul.dev.customerservice.exceptions.EmailExistedException;
import soul.dev.customerservice.mapper.CustomerMapper;
import soul.dev.customerservice.repos.CustomerRepo;

import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepo customerRepo;
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepo customerRepo, CustomerMapper customerMapper) {
        this.customerRepo = customerRepo;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) throws EmailExistedException {
        Customer customer = customerMapper.fromCustomerDto(customerDto) ;
        Optional<Customer> customerByEmail = customerRepo.findCustomerByEmail(customer.getEmail());
        if(customerByEmail.isPresent()) {
            throw new EmailExistedException("email already existed");
        }
        return customerMapper.fromCustomer(customerRepo.save(customer));

    }
}
