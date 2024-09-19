package soul.dev.customerservice.service;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import soul.dev.customerservice.Dtos.CustomerDto;
import soul.dev.customerservice.entities.Customer;
import soul.dev.customerservice.exceptions.EmailExistedException;
import soul.dev.customerservice.mapper.CustomerMapper;
import soul.dev.customerservice.repos.CustomerRepo;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock
    private CustomerRepo customerRepo;
    @Mock
    private CustomerMapper customerMapper;
    @InjectMocks
    private CustomerServiceImpl underTest;

    @Test
    void createCustomer() throws EmailExistedException {
        CustomerDto customerDto = CustomerDto.builder()
                .firstName("soulaiman").lastName("ghailan").email("soulaiman@gmail.com").build() ;
        Customer customer = Customer.builder()
                .firstName("soulaiman").lastName("ghailan").email("soulaiman@gmail.com").build() ;
        Customer savedCustomer = Customer.builder()
                .id(1L).firstName("soulaiman").lastName("ghailan").email("soulaiman@gmail.com").build() ;
        CustomerDto expectedCustomerDto = CustomerDto.builder()
                .id(1L).firstName("soulaiman").lastName("ghailan").email("soulaiman@gmail.com").build() ;

        Mockito.when(customerRepo.findCustomerByEmail(customerDto.getEmail())).thenReturn(Optional.empty()) ;
        Mockito.when(customerMapper.fromCustomerDto(customerDto)).thenReturn(customer) ;
        Mockito.when(customerRepo.save(customer)).thenReturn(savedCustomer);
        Mockito.when(customerMapper.fromCustomer(savedCustomer)).thenReturn(expectedCustomerDto) ;

        CustomerDto result = underTest.createCustomer(customerDto) ;
        AssertionsForClassTypes.assertThat(result).isNotNull() ;
        AssertionsForClassTypes.assertThat(expectedCustomerDto).isEqualTo(result) ;
    }

    @Test
    void shoudNotcreateCustomerWithExistedEmail() throws EmailExistedException {
        CustomerDto customerDto = CustomerDto.builder()
                .firstName("soulaiman").lastName("ghailan").email("soulaiman@gmail.com").build() ;
        Customer customer = Customer.builder()
                .id(3L).firstName("soulaiman").lastName("ghailan").email("soulaiman@gmail.com").build() ;

        Mockito.when(customerMapper.fromCustomerDto(customerDto)).thenReturn(customer) ;
        Mockito.when(customerRepo.findCustomerByEmail(customerDto.getEmail())).thenReturn(Optional.of(customer)) ;
        AssertionsForClassTypes.assertThatThrownBy(() -> underTest.createCustomer(customerDto)).isExactlyInstanceOf(EmailExistedException.class);
    }
}