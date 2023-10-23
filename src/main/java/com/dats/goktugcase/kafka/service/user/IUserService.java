package com.dats.goktugcase.kafka.service.user;

import com.dats.goktugcase.kafka.model.UserCreateDto;
import com.dats.goktugcase.kafka.model.UserEntity;

import java.util.List;

public interface IUserService {

    UserEntity save(UserCreateDto userCreateDto);

    List<UserEntity> findAll();
}
