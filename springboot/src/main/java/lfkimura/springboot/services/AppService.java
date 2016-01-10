package lfkimura.springboot.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lfkimura.springboot.amqp.RabbitPublisher;

@Service
public class AppService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RabbitPublisher publisher;

    public boolean pushNotification(String message) throws Exception {

        log.info("processing message TO QUEUE");
        publisher.publish(message);
        return true;
    }
}
