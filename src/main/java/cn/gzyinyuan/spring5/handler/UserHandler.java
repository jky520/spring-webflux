package cn.gzyinyuan.spring5.handler;

import cn.gzyinyuan.spring5.entity.User;
import cn.gzyinyuan.spring5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * 创建一个包含一些方法的 Handler 类用来处理 HTTP 请求
 * Created by DT人 on 2017/10/9 20:20.
 */
@Service
public class UserHandler {

    @Autowired
    private UserRepository userRepository;

    public Mono<ServerResponse> handleGetUsers(ServerRequest request) {
        return ServerResponse.ok().body(userRepository.getUsers(), User.class);
    }

    public Mono<ServerResponse> handlerGetUserById(ServerRequest request) {
        return userRepository.getUserById(request.pathVariable("id"))
                        .flatMap(user -> ServerResponse.ok().body(Mono.just(user), User.class))
                        .switchIfEmpty(ServerResponse.notFound().build());
    }
}
