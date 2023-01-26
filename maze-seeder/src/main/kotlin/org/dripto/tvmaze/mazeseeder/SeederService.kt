package org.dripto.tvmaze.mazeseeder

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SeederService(
    private val client: TvMazeClient,
    private val rabbitTemplate: RabbitTemplate,
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {
    private var showId = 1
    @Scheduled(initialDelay = 1000, fixedDelay = 2000)
    fun seedShows() {
        val show = client.getShow((showId++).toString())
        //rabbitTemplate.convertAndSend(topicExchangeName, showRouteKey, show)
        kafkaTemplate.send("show", UUID.randomUUID().toString(), show)
    }
}