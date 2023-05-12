package com.emreakin.service;

import com.emreakin.model.CompanyModel;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ConsumerService {

    @KafkaListener(topics = "${kafka.topics.user.name}",
            groupId = "${kafka.topics.user.group}",
            containerFactory = "userKafkaListenerFactory")
    public void consumeUser(ConsumerRecord<String, schema.avro.User> record) {
        schema.avro.User user = record.value();
        System.out.println("User name : " + user.getName());
    }

    @KafkaListener(topics = "${kafka.topics.company.name}",
            groupId = "${kafka.topics.company.group}",
            containerFactory = "companyKafkaListenerFactory")
    public void consumeCompany(ConsumerRecord<String, CompanyModel> record) {
        CompanyModel company = record.value();
        System.out.println("Company name : " + company.getName());
    }

    @KafkaListener(topics = "${kafka.topics.message.name}",
            groupId = "${kafka.topics.message.group}",
            containerFactory = "messageIdKafkaListenerFactory")
    public void consumeMessageId(ConsumerRecord<String, String> record) {
        System.out.println("Message Id : " + record.value());
    }
}
