package com.felipecpdev.springkafkaproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic generateTopic() {
        Map<String, String> configurations
                = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG
                , TopicConfig.CLEANUP_POLICY_DELETE);//se borra el mensaje si no se usa, compact(mantiene el mas actualizado)
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");// ms tiempo de retencion de mensajes - por defecto -1
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");// bytes tamaño maximo de cada segmento
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000000");// por defecto 1MB

        return TopicBuilder
                .name("test-topic")
                .partitions(2)// el topic se divide en 2 paticiones
                .replicas(1)// replicas en otro topic o cluster
                .configs(configurations)
                .build();
    }
}
