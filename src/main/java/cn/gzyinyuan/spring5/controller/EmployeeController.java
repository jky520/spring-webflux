package cn.gzyinyuan.spring5.controller;

import cn.gzyinyuan.spring5.entity.Employee;
import cn.gzyinyuan.spring5.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Created by DT人 on 2017/10/11 17:36.
 */
@RestController
public class EmployeeController {
    /**
     * 扩展ReactiveCrudRepository接口，提供基本的CRUD操作
     */
    @Autowired
    private final EmployeeRepository employeeRepository;

    /**
     * spring-boot-starter-data-mongodb-reactive提供的通用模板
     */
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public EmployeeController(EmployeeRepository employeeRepository, ReactiveMongoTemplate reactiveMongoTemplate) {
        this.employeeRepository = employeeRepository;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @GetMapping("/reactive/employee")
    public Flux<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/reactive/delay/employee")
    public Flux<Employee> findAllDelay() {
        return employeeRepository.findAll().delayElements(Duration.ofSeconds(1));
    }

    @GetMapping("/reactive/employee/{id}")
    public Mono<Employee> get(@PathVariable String id) {
        return employeeRepository.findById(id);
    }

    @PostMapping("/reactive/employee")
    public Flux<Employee> create(@RequestBody Flux<Employee> restaurants) {
        return restaurants
                .buffer(10000)
                .flatMap(rs -> reactiveMongoTemplate.insert(rs, Employee.class));
    }

    @DeleteMapping("/reactive/employee/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return employeeRepository.deleteById(id);
    }

}
