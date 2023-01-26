package org.dripto.tvmaze.mazeseeder

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class KafkaConfig {
    @Bean
    fun showTopic(): NewTopic = TopicBuilder.name("show").partitions(3).replicas(1).build()
}