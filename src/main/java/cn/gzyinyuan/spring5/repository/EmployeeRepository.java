package cn.gzyinyuan.spring5.repository;

import cn.gzyinyuan.spring5.entity.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * 数据持久层
 * Created by DT人 on 2017/10/11 17:26.
 */
public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Long> {

}
