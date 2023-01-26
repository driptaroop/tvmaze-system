package org.dripto.tvmaze.mazeseeder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class MazeSeederApplication

fun main(args: Array<String>) {
	runApplication<MazeSeederApplication>(*args)
}
