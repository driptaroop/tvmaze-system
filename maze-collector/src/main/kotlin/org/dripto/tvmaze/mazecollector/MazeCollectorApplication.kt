package org.dripto.tvmaze.mazecollector

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories

@SpringBootApplication
@EnableCassandraRepositories
class MazeCollectorApplication

fun main(args: Array<String>) {
	runApplication<MazeCollectorApplication>(*args)
}
