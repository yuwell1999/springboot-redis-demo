package com.example.redisdemo.controller;

import com.example.redisdemo.redisUtils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

//    private final ReactiveRedisOperations<String, Student> studentOps;
//
//    StudentController(ReactiveRedisOperations<String, Student>studentOps) {
//        this.studentOps = studentOps;
//    }
//
//    @GetMapping("/students")
//    public Flux<Student> all() {
//        return studentOps.keys("*").flatMap(studentOps.opsForValue()::get);
//    }

//    @GetMapping(value = "/{id}")
//    public Mono<Student> findStudentById(@PathVariable("id") String id){
//        String key = "student_"+id;
//        ValueOperations<String,Student> operations = redisTemplate.opsForValue();
//        boolean hasKey = redisTemplate.hasKey(key);
//        Student student = operations.get(key);
//
//        if(!hasKey){
//            return Mono.create(monoSink -> monoSink.success(null));
//        }
//        return Mono.create(monoSink->monoSink.success(student));
//    }
//
//    @PostMapping()
//    public Mono<Student> savaStudent(@RequestBody Student studnet){
//        String key = "student_" + studnet.getId();
//        ValueOperations<String, Student> operations = redisTemplate.opsForValue();
//        operations.set(key,studnet,60, TimeUnit.SECONDS);
//
//        return Mono.create(monoSink->monoSink.success(studnet));
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public Mono<Integer> deleteStudent(@PathVariable("id") int id){
//        String key = "student_"+id;
//        boolean hasKey = redisTemplate.hasKey(key);
//        //Student student = operations.get(key);
//
//        if(hasKey){
//            redisTemplate.delete(key);
//        }
//        return Mono.create(monoSink->monoSink.success(id));
//    }

    @GetMapping("/get/{id}")
    public Object getStudents(@PathVariable("id") String id) {
        System.out.println(redisUtils.getExpire(id));
        return redisUtils.get(id);
    }

    @GetMapping("/set/{id}/{name}")
    public Object setStudents(@PathVariable("id") String id, @PathVariable("name") String name) {
        redisUtils.set(id, name);
        System.out.println(redisUtils.getExpire(id));
        return redisUtils.get(id);
    }

    @GetMapping("/delete/{id}")
    public Object deleteById(@PathVariable("id") String id) {
        redisUtils.del(id);
        //System.out.println(redisUtils.getExpire(id));
        return redisUtils.get(id);
    }
}
