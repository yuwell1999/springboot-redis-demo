package com.example.redisdemo;

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
public class StudentLoader {
    private final ReactiveRedisConnectionFactory factory;
    private final ReactiveRedisOperations<String, Student> studentOps;

    public StudentLoader(ReactiveRedisConnectionFactory factory, ReactiveRedisOperations<String, Student> studentOps) {
        this.factory = factory;
        this.studentOps = studentOps;
    }

    @PostConstruct
    public void loadData() {
        factory.getReactiveClusterConnection().serverCommands().flushAll().thenMany(
                Flux.just("AAA Redis", "BBB Redis", "CCC Redis")
                        .map(name -> new Student(UUID.randomUUID().toString(), name))
                        .flatMap(student -> studentOps.opsForValue().set(student.getId(), student))
        ).thenMany(studentOps.keys("*")
                .flatMap(studentOps.opsForValue()::get))
                .subscribe(System.out::println);
    }
}
