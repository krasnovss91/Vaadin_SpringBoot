package com;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudWithVaadinApplication {
    private static final Logger log = LoggerFactory.getLogger(CrudWithVaadinApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CrudWithVaadinApplication.class, args);
    }

    //Список использованных технологий: Spring Boot, Spring Data JPA, H2 Database, Vaadin, Lombok, LoggerFactory
    @Bean
    public CommandLineRunner loadData(CustomerRepository repository) {
        return (args) -> {

            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));


            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");


            Customer customer = repository.findById(1L).get();
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastNameStartsWithIgnoreCase('Bauer'):");
            log.info("--------------------------------------------");
            for (Customer bauer : repository
                    .findByLastNameStartsWithIgnoreCase("Bauer")) {
                log.info(bauer.toString());
            }
            log.info("");
        };
    }
}
