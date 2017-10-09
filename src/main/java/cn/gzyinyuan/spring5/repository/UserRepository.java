package cn.gzyinyuan.spring5.repository;

import cn.gzyinyuan.spring5.entity.User;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * Created by DT人 on 2017/10/9 19:46.
 */
@Repository
public class UserRepository {
    private final List<User> users = Arrays.asList(new User(1L, "张三"), new User(2L, "李四"), new User(3L, "王五"));

    /**
     * Mono 是一个用来发送 0 或者单值数据的发布器
     * GetUserById() 返回一个 Mono<User> 表示其在数据可用的情况下发送 0 个或者单个用户
     * @param id
     * @return
     */
    public Mono<User> getUserById(String id) {
        return Mono.justOrEmpty(users.stream().filter(user -> {
            return user.getId().equals(Long.valueOf(id));
        }).findFirst().orElse(null));
    }

    /**
     * Flux 可以用来发送 0 到 N 个值
     * GetUsers() 返回一个用户列表的 Flux 实例，表示其发送 0 到多个用户数据
     * @return
     */
    public Flux<User> getUsers() {
        return Flux.fromIterable(users);
    }
}
