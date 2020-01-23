package com.dataart.edu.ms.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.amqp.RabbitHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.dataart.edu.ms.event")
@EnableJpaRepositories("com.dataart.edu.ms.event.repository")
@EntityScan("com.dataart.edu.ms.event.domain")
public class MessagingConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(MessagingConfiguration.class);

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${app.rabbit.exchange_name}")
    private String exchangeName;

    @Value("${app.rabbit.active_queue}")
    private String queueName;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    public FanoutExchange fanouotExchange() {
        FanoutExchange fanoutExchange = new FanoutExchange(exchangeName);
        return fanoutExchange;
    }

    @Bean
    public Binding fanoutBinding() {
        return BindingBuilder.bind(queue()).to(fanouotExchange());
    }

    @Bean
    public SimpleRabbitListenerContainerFactory broadcastContainer(ConnectionFactory connectionFactory, SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public HealthIndicator rabbitHealthIndicator(RabbitTemplate rabbitTemplate) {
        return new RabbitHealthIndicator(rabbitTemplate) {
            @Override
            protected void doHealthCheck(Health.Builder builder) throws Exception {
                super.doHealthCheck(builder);
                builder
                        .withDetail("queueName", queueName)
                        .withDetail("exchangeName", exchangeName);
            }
        };
    }

}

