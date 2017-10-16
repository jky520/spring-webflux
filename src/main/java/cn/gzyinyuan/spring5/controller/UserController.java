package cn.gzyinyuan.spring5.controller;

import cn.gzyinyuan.spring5.entity.Employee;
import cn.gzyinyuan.spring5.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Created by DT人 on 2017/10/9 20:49.
 */
@RestController()
public class UserController {

    public Mono<ServerResponse> handlerGetUsers() {
        return WebClient.create("http://localhost:9000").get().uri("/api/user")
                .accept(MediaType.APPLICATION_JSON)
                .exchange().flatMap(resp -> ServerResponse.ok().body(resp.bodyToFlux(User.class), User.class));
    }

    /**
     * flatMap的使用例子
     * {{1,2}，{3,4}，{5,6}}  - > flatMap  - > {1,2,3,4,5,6}
     *
     * {'a'，'b'}，{'c'，'d'}，{'e'，'f'}}  - > flatMap  - > {'a'，'b'，'c' D”， 'E'， 'F'}
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public Mono<ServerResponse> handleGetUserById(@PathVariable String id) {
        return WebClient.create("http://localhost:9000").get().uri("/api/user/" + id)
                .accept(MediaType.APPLICATION_JSON).exchange().flatMap(resp -> ServerResponse.ok().body(resp.bodyToMono(User.class), User.class));
    }

    @GetMapping("/employee/{id}")
    public Mono<ServerResponse> handleGetEmployeeById(@PathVariable String id) {
        return WebClient.create("http://localhost:8088").get().uri("/reactive/employee/59dde864a1393b7cb208d8d2")
                .accept(MediaType.APPLICATION_JSON).exchange().flatMap(resp -> ServerResponse.ok().body(resp.bodyToMono(Employee.class), Employee.class));
                //.flatMap(resp -> ServerResponse.ok().body(Mono.just(resp), Employee.class))
               // .switchIfEmpty(ServerResponse.notFound().build());
    }
 }
