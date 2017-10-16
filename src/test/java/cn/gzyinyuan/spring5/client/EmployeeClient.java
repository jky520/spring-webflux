package cn.gzyinyuan.spring5.client;

import cn.gzyinyuan.spring5.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by DT人 on 2017/10/16 11:43.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeClient {

    @Test
    public void testGetEmployeeById() {
        Employee employee = WebClient.create("http://localhost:8088").get().uri("/reactive/employee/59dde864a1393b7cb208d8d2")
                .accept(MediaType.APPLICATION_JSON).exchange().flatMap(resp -> resp.bodyToMono(Employee.class)).block();
        System.out.println(employee);
    }
}
