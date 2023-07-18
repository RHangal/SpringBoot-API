package com.firstspringcode;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customer")
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }


    record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ){

    }

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }

    record UpdateCustomerRequest(
            String name,
            String email,
            Integer age
    ){

    }

    @PutMapping("{customerId}")
    public void updateCustomer(
            @PathVariable("customerId") Integer id,
            @RequestBody UpdateCustomerRequest request){
            Optional<Customer> update = customerRepository.findById(id);
            Customer customer = update.get();
            customer.setName(request.name);
            customer.setEmail(request.email);
            customer.setAge(request.age);
            customerRepository.save(customer);






    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

//    @GetMapping("/")
//    public String greet(){
//        return "Hello";
//    }
//
//    @GetMapping("/greet")
//    public GreetRes greetRes(){
//        GreetRes res = new GreetRes(
//                "Hello",
//                List.of("Java", "C++", "JavaScript"),
//                new Person("Zeek", 25, 4000)
//                );
//        return res;
//    }
//
//    record Person(String name, int age, double cash){
//
//    }
//    record GreetRes(
//            String greet,
//            List<String> favProgrammingLangs,
//            Person person


//    ){}


}
