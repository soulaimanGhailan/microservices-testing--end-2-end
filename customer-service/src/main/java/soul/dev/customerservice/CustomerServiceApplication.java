package soul.dev.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import soul.dev.customerservice.entities.Customer;
import soul.dev.customerservice.repos.CustomerRepo;

@SpringBootApplication
public class CustomerServiceApplication {

    @Autowired
    private CustomerRepo customerRepo ;

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    @Profile("!test")
    CommandLineRunner run(){
        return args -> {
            customerRepo.save(Customer.builder()
                    .firstName("soulaiman").lastName("ghailan").email("soulaiman@gmail.com").build()) ;
            customerRepo.save(Customer.builder()
                    .firstName("mohammed").lastName("ghailan").email("mohammed@gmail.com").build()) ;
            customerRepo.save(Customer.builder()
                    .firstName("jamal").lastName("ghailan").email("jamal@gmail.com").build()) ;

        };
    }

}
