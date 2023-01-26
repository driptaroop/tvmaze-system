package org.dripto.tvmaze.mazecollector

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class QueueConfig {
    @Bean
    fun jackson2JsonMessageConverter(objectMapper: ObjectMapper) = Jackson2JsonMessageConverter(objectMapper)
    @Bean @Primary
    fun objectmapper(): ObjectMapper = jacksonObjectMapper().findAndRegisterModules()
}

const val queueName = "q.showQueue"