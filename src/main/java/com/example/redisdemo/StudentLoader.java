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

    public StudentLoader(ReactiveRedisConnectionFactory factory, ReactiveRedisOperations<String, Student> coffeeOps) {
        this.factory = factory;
        this.studentOps = coffeeOps;
    }

    @PostConstruct
    public void loadData() {
        factory.getReactiveConnection().serverCommands().flushAll().thenMany(
                Flux.just("Jet Black Redis", "Darth Redis", "Black Alert Redis")
                        .map(name -> new Student(UUID.randomUUID().toString(), name))
                        .flatMap(coffee -> studentOps.opsForValue().set(coffee.getId(), coffee)))
                .thenMany(studentOps.keys("*")
                        .flatMap(studentOps.opsForValue()::get))
                .subscribe(System.out::println);
    }
}
