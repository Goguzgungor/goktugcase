package com.dats.goktugcase.kafka.service.consumer;

import org.springframework.kafka.annotation.KafkaListener;

public interface IConsumerService {


    void consume(String message);

}
