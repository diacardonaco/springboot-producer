package com.todo1.todo1.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {


    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    private final String KAFKA_TOPIC = "todo1";

    public void send(String message){
        kafkaTemplate.send(KAFKA_TOPIC,message);
    }

}
