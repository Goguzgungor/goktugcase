package com.dats.goktugcase.kafka.service.user;


import com.dats.goktugcase.kafka.model.Role;
import com.dats.goktugcase.kafka.model.UserCreateDto;
import com.dats.goktugcase.kafka.model.UserEntity;
import com.dats.goktugcase.kafka.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{


    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity save(UserCreateDto userCreateDto) {
        if(userCreateDto.getRole() == null) {
            userCreateDto.setRole(Role.USER);
        }
        UserEntity templateUserEntity = new UserEntity(userCreateDto.getName(), userCreateDto.getPassword(), userCreateDto.getRole());
        return userRepository.save(templateUserEntity);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }
}
