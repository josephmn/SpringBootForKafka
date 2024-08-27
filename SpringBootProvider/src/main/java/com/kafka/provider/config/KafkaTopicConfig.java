package com.kafka.provider.config;

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

        Map<String, String> configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE); // delete (Borra el mensaje), despues de X cierto tiempo el mensaje no se necesita se borra - compact (Mantiene el mas actual).
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); // tiempo de retencion del mensaje (86400000 = 1 dia, expresado en milisegundos) - defecto: -1, no se borra nunca.
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824"); // cual es el tamaño máximo que puede tener los segmentos dentro del topic, 1073741824 es un 1GB en bytes - defecto: 1073741824 (1GB).
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012"); // cual es el tamaño máximo de cada mensaje - defecto: 1MB.

        return TopicBuilder.name("topic-message")
                .partitions(2)
                .replicas(2)
                .configs(configurations)
                .build();
    }
}
