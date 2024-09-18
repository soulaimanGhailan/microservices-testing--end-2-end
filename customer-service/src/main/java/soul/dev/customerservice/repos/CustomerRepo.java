package soul.dev.customerservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import soul.dev.customerservice.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByEmail(String email);
    List<Customer> findCustomersByLastNameIgnoreCase(String name ) ;
}
