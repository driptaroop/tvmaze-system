package org.dripto.tvmaze.mazeseeder

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Declarables
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class QueueConfig {
    @Bean
    fun topicBindings(): Declarables {
        val showQueue = Queue(queueName, false)
        val topicExchange = TopicExchange(topicExchangeName)
        return Declarables(
            showQueue, topicExchange,
            BindingBuilder.bind(showQueue).to(topicExchange).with(showRouteKey),
        )
    }

    @Bean
    fun jackson2JsonMessageConverter(objectMapper: ObjectMapper) = Jackson2JsonMessageConverter(objectMapper)
}

const val queueName = "q.showQueue"
const val showRouteKey = "show"
const val topicExchangeName = "tvmaze"
