package org.dripto.tvmaze.mazeseeder

import org.dripto.tvmaze.model.Show
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.service.annotation.GetExchange

interface TvMazeClient {
    @GetExchange("/shows/{showId}")
    fun getShow(@PathVariable("showId") showId: String): Show
}


