package com.example.redisdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MessagingRedisApplication {

    //private static final Logger LOGGER = LoggerFactory.getLogger(MessagingRedisApplication.class);

//    public static void main(String[] args) throws InterruptedException, IOException {
//
//        ApplicationContext ctx = SpringApplication.run(MessagingRedisApplication.class, args);
//
//        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
//        Receiver receiver = ctx.getBean(Receiver.class);
//
//        while (receiver.getCount() == 0) {
//
//            LOGGER.info("Sending message...");
//            template.convertAndSend("chat", "Hello from Redis!");
//            Thread.sleep(500L);
//        }
//
//        JsonRequest jsonRequest = new JsonRequest();
//        //System.exit(0);
//    }

//    @Bean
//    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
//
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
//
//        return container;
//    }
//
//    @Bean
//    MessageListenerAdapter listenerAdapter(Receiver receiver) {
//        return new MessageListenerAdapter(receiver, "receiveMessage");
//    }
//
//    @Bean
//    Receiver receiver() {
//        return new Receiver();
//    }
//
//    @Bean
//    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
//        return new StringRedisTemplate(connectionFactory);
//    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(MessagingRedisApplication.class, args);
        //JsonRequest jsonRequest = new JsonRequest();
    }

}
