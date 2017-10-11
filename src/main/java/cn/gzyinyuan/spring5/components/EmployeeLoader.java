package cn.gzyinyuan.spring5.components;

import cn.gzyinyuan.spring5.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by DT人 on 2017/10/11 17:24.
 */
@Component
public class EmployeeLoader implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... strings) throws Exception {

    }
}
