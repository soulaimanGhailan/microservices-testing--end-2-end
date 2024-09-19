package soul.dev.customerservice.mapper;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import soul.dev.customerservice.Dtos.CustomerDto;
import soul.dev.customerservice.entities.Customer;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    CustomerMapper mapper = new CustomerMapper();
    
    @Test
    void fromCustomer() {
        Customer customer = Customer.builder()
                .id(1l).firstName("soulaiman").lastName("ghailan").email("soulaiman@gmail.com").build() ;

        CustomerDto expected = CustomerDto.builder()
                .id(1l).firstName("soulaiman").lastName("ghailan").email("soulaiman@gmail.com").build() ;
        CustomerDto customerDto = mapper.fromCustomer(customer);
        assertThat(customerDto).isEqualTo(expected) ;
    }

    @Test
    void fromCustomerDto() {
        Customer expected = Customer.builder()
                .id(1l).firstName("soulaiman").lastName("ghailan").email("soulaiman@gmail.com").build() ;

        CustomerDto customerDto = CustomerDto.builder()
                .id(1l).firstName("soulaiman").lastName("ghailan").email("soulaiman@gmail.com").build() ;
        Customer customer = mapper.fromCustomerDto(customerDto);
        assertThat(customer).isEqualTo(expected) ;
    }

    @Test
    void fromCustomers() {
        List<CustomerDto> expectedResult = List.of(
                CustomerDto.builder().firstName("soulaiman").lastName("ghailan").email("soulaiman@gmail.com").build() ,
                CustomerDto.builder().firstName("mohammed").lastName("ghailan").email("mohammed@gmail.com").build() ,
                CustomerDto.builder().firstName("jamal").lastName("ghailan").email("jamal@gmail.com").build()
        ) ;

        List<Customer> customers = List.of(
                Customer.builder().firstName("soulaiman").lastName("ghailan").email("soulaiman@gmail.com").build() ,
                Customer.builder().firstName("mohammed").lastName("ghailan").email("mohammed@gmail.com").build() ,
                Customer.builder().firstName("jamal").lastName("ghailan").email("jamal@gmail.com").build()
        ) ;

        List<CustomerDto> customerDtos  = mapper.fromCustomers(customers) ;
        assertThat(expectedResult).isNotNull() ;
        assertThat(customerDtos).usingRecursiveComparison().isEqualTo(expectedResult) ;
    }
}