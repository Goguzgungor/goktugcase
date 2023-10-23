package com.dats.goktugcase.kafka.service;

import com.dats.goktugcase.kafka.model.UserCreateDto;
import com.dats.goktugcase.kafka.model.UserEntity;

import java.util.List;

public interface IKafkaService {

    void sendMessage(UserCreateDto userCreateDto);


    List<UserEntity> reciveData();
}
