package com.dats.goktugcase.kafka.controller;

import com.dats.goktugcase.kafka.helper.MessageState;
import com.dats.goktugcase.kafka.helper.StateEnum;
import com.dats.goktugcase.kafka.model.UserCreateDto;
import com.dats.goktugcase.kafka.model.UserEntity;
import com.dats.goktugcase.kafka.service.IKafkaService;
import com.dats.goktugcase.kafka.service.producer.IProducerService;
import com.dats.goktugcase.kafka.service.producer.ProducerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/kafka")
public class KafkaController {

    private IKafkaService kafkaService;
    private MessageState messageState;

    @Autowired
    public KafkaController(IKafkaService kafkaService, MessageState messageState) {
        this.kafkaService = kafkaService;
        this.messageState = messageState;
    }



    @PostMapping("/publish")
    public List<UserEntity> publish(@RequestBody UserCreateDto userCreateDto) {
      kafkaService.sendMessage(userCreateDto);
      messageState.setState(StateEnum.SEND);
      return kafkaService.reciveData();
    }
}
