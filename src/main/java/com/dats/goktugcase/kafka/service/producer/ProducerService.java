package com.dats.goktugcase.kafka.service.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ProducerService  implements IProducerService {

	private final KafkaTemplate<String, String> kafkaTemplate;

	@Value("${spring.kafka.case-topic}")
	private String topicName;

	public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}


	@Override
	public void sendMessage(String msg) {
		kafkaTemplate.send(topicName, UUID.randomUUID().toString(), msg);

		log.info("Message sent to kafka topic: {}", msg);

	}
}