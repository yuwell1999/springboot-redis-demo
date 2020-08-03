package com.example.redisdemo;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class StudentController {
    private final ReactiveRedisOperations<String, Student> studentOps;

    public StudentController(ReactiveRedisOperations<String, Student> studentOps) {
        this.studentOps = studentOps;
    }

    @GetMapping("/students")
    public Flux<Student> all() {
        return studentOps.keys("*").flatMap(studentOps.opsForValue()::get);
    }
}
