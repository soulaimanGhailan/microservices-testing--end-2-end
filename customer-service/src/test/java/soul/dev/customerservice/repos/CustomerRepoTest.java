package soul.dev.customerservice.repos;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import soul.dev.customerservice.entities.Customer;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class CustomerRepoTest {
    @Autowired
    private CustomerRepo customerRepo;

    @BeforeEach
      public void setUp() {
        customerRepo.save(Customer.builder()
                .firstName("soulaiman").lastName("ghailan").email("soulaiman@gmail.com").build()) ;
        customerRepo.save(Customer.builder()
                .firstName("mohammed").lastName("ghailan").email("mohammed@gmail.com").build()) ;
        customerRepo.save(Customer.builder()
                .firstName("jamal").lastName("ghailan").email("jamal@gmail.com").build()) ;
    }
    @Test
    public void testFindByExistedEmail(){
        String email = "soulaiman@gmail.com" ;
        Optional<Customer> customerByEmail = customerRepo.findCustomerByEmail(email);
        AssertionsForClassTypes.assertThat(customerByEmail).isPresent();
        Assert.assertEquals(email , customerByEmail.get().getEmail());
    }
    @Test
    public void testFindByNotExistedEmail(){
        String email = "xxxxxxxxxxxx@gmail.com" ;
        Optional<Customer> customerByEmail = customerRepo.findCustomerByEmail(email);
        AssertionsForClassTypes.assertThat(customerByEmail).isNotPresent();
    }

    @Test
    public void testFindByLastName(){
        String lastname = "GHAILAN" ;
        List<Customer> expectedResult = List.of(
                Customer.builder().firstName("soulaiman").lastName("ghailan").email("soulaiman@gmail.com").build() ,
                Customer.builder().firstName("mohammed").lastName("ghailan").email("mohammed@gmail.com").build() ,
                Customer.builder().firstName("jamal").lastName("ghailan").email("jamal@gmail.com").build()
        ) ;
        List<Customer> customers = customerRepo.findCustomersByLastNameIgnoreCase(lastname);
        AssertionsForClassTypes.assertThat(customers).isNotNull() ;
        AssertionsForClassTypes.assertThat(customers).usingRecursiveComparison().ignoringFields("id").isEqualTo(expectedResult) ;

    }


}