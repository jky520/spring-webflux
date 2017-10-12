package cn.gzyinyuan.spring5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Version;

/**
 * 雇佣测试实体
 * Created by DT人 on 2017/10/11 17:19.
 */
@Data
@Entity
//@Document
public class Employee {

    @Id
    @GeneratedValue
    private String id;
    private String firstName;
    private String lastName;
    private String description;

    @Version
    @JsonIgnore
    private String version;

    private Employee() {
    }

    public Employee(String firstName, String lastName, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
    }
}
