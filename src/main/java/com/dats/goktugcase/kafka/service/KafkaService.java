package com.dats.goktugcase.kafka.service;

import com.dats.goktugcase.kafka.helper.MessageState;
import com.dats.goktugcase.kafka.helper.StateEnum;
import com.dats.goktugcase.kafka.model.UserCreateDto;
import com.dats.goktugcase.kafka.model.UserEntity;
import com.dats.goktugcase.kafka.service.consumer.IConsumerService;
import com.dats.goktugcase.kafka.service.producer.IProducerService;
import com.dats.goktugcase.kafka.service.user.IUserService;
import com.dats.goktugcase.kafka.service.user.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class KafkaService implements  IKafkaService{


    private final IProducerService producerService;
    private final IUserService userService;
    private final IConsumerService consumerService;

    private final MessageState messageState;

    @Autowired
    public KafkaService(IProducerService producerService, IUserService userService, IConsumerService consumerService, MessageState messageState) {
        this.producerService = producerService;
        this.messageState = messageState;
        this.consumerService = consumerService;
        this.userService = userService;
    }


    @Override
    public void sendMessage(UserCreateDto userCreateDto) {


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String queuePayloadString = objectMapper.writeValueAsString(userCreateDto);
            System.out.println(queuePayloadString);
            producerService.sendMessage(queuePayloadString);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public List<UserEntity> reciveData() {
        long startTime = System.currentTimeMillis();
        while (messageState.getState().equals(StateEnum.SEND)){
            long currentTime = System.currentTimeMillis(); // Şu anki zamanı al
            long elapsedTime = currentTime - startTime;
            if(elapsedTime > 3000){
                log.error("Time out");
                break;
            }
            log.info("Waiting for message");
        }
        return userService.findAll();
    }
}
