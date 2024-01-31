package org.delivery.api.config.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    
    // 1. Queue 에 대한 설정

    // Exchange 생성
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("delivery.exchange");
    }
    
    // Queue 생성
    @Bean
    public Queue queue() {
        return new Queue("delivery.queue");
    }
    
    // Binding 설정
    @Bean
    public Binding binding(DirectExchange directExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(directExchange).with("delivery.key");
    }
    
    
    // 2. 기타 설정
    
    // org.springframework.amqp.rabbit.connection.ConnectionFactory,
    // org.springframework.amqp.support.converter.MessageConverter
    // ConnectionFactory 는 application.yaml 의 설정을 따라 RabbitTemplate 에서
    //  자동으로 채워서 넘겨줌
    @Bean
    public RabbitTemplate rabbitTemplate(
        ConnectionFactory connectionFactory,
        MessageConverter messageConverter
    ) {
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        
        return rabbitTemplate;
    }
    
    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}




















