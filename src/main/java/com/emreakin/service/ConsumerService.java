package com.emreakin.service;

import com.emreakin.model.CompanyModel;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ConsumerService {

    @RetryableTopic(include = {NullPointerException.class, ArrayIndexOutOfBoundsException.class},
            attempts = "4",
            backoff = @Backoff(delay = 1000, multiplier = 2),
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
            retryTopicSuffix = "testR",
            dltTopicSuffix = "testDllt")
    @KafkaListener(topics = "${kafka.topics.user.name}",
            groupId = "${kafka.topics.user.group}",
            containerFactory = "userKafkaListenerFactory")
    public void consumeUser(ConsumerRecord<String, schema.avro.User> record) {
        schema.avro.User user = record.value();
        user = null;
        System.out.println("***********************************");
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
