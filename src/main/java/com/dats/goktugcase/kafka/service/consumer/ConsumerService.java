package com.dats.goktugcase.kafka.service.consumer;

import com.dats.goktugcase.kafka.helper.MessageState;
import com.dats.goktugcase.kafka.helper.StateEnum;
import com.dats.goktugcase.kafka.model.UserCreateDto;
import com.dats.goktugcase.kafka.service.user.IUserService;
import com.dats.goktugcase.kafka.service.user.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService implements IConsumerService {
    private String lastMessage;

    private final IUserService userService;
    private final MessageState messageState ;

    @Autowired
    public ConsumerService(IUserService userService, MessageState messageState) {
        this.userService = userService;
        this.messageState = messageState;
    }


    @Value("${spring.kafka.case-topic}")
    private static String topic;

    @KafkaListener(
            topicPartitions = @TopicPartition(topic = "${spring.kafka.case-topic}",
                    partitionOffsets = {
                            @PartitionOffset(partition = "0", initialOffset = "0"),
}))
    @Override
    public void consume(String massage) {
        final Object lock = new Object();
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<UserCreateDto> mapType = new TypeReference<>() {};
        try {
                UserCreateDto payload = objectMapper.readValue(massage, mapType);
                log.info("Message kaydedilmeye hazır: {}", payload);
                userService.save(payload);
                log.warn("Message kaydedildi: {}", payload);

        } catch (Exception e) {
            System.out.println(e);
        }
        messageState.setState(StateEnum.ARRIVED);
    }

}
