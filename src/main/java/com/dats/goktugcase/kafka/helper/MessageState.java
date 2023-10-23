package com.dats.goktugcase.kafka.helper;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.stereotype.Service;

@Service
public class MessageState {

    @Enumerated(EnumType.STRING)
    private StateEnum state;

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }
}
