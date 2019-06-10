package me.freelee.betterday.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Date:2019/2/23
 *
 * @author:Lee
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue Queue(){
        return new Queue("hello");
    }

    @Bean
    public Queue eqptQueue(){
        return new Queue("eqpt_queue");
    }

}
