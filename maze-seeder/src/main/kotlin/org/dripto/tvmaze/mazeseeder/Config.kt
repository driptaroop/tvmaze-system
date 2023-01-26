package org.dripto.tvmaze.mazeseeder

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class Config {
    @Bean
    fun tvMazeClient(builder: WebClient.Builder): TvMazeClient {
        val webClient = builder.baseUrl("https://api.tvmaze.com").build()
        return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build()
            .createClient(TvMazeClient::class.java)
    }

}

