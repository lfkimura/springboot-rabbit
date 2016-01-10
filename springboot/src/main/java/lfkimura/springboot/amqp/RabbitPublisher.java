package lfkimura.springboot.amqp;

import org.slf4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class RabbitPublisher {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final String queueName = "simple-queue";
	private final String topicName = "simple-exchange";
	private final CachingConnectionFactory fact;
	private final RabbitTemplate client;

	public RabbitPublisher() {

		log.info("Initializing publisher");

		fact = new CachingConnectionFactory("localhost");
		final RabbitAdmin admin = new RabbitAdmin(fact);
		final Queue queue = new Queue(queueName);
		admin.declareQueue(queue);
		final TopicExchange exchange = new TopicExchange(topicName);
		admin.declareExchange(exchange);
		admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(queueName));
		client = new RabbitTemplate(fact);
		client.setMessageConverter(new Jackson2JsonMessageConverter());
	}

	public void publish(String request) throws Exception {

		try {
			log.info(String.format("Publishing %s to queue %s", request, queueName));
			client.convertAndSend(queueName, request);
		} catch (final Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
