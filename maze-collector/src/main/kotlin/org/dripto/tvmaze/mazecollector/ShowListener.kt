package org.dripto.tvmaze.mazecollector

import com.datastax.oss.driver.api.querybuilder.QueryBuilder
import com.datastax.oss.driver.api.querybuilder.select.Selector
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.dripto.tvmaze.mazecollector.cassandra.ShowRepository
import org.dripto.tvmaze.mazecollector.cassandra.po
import org.dripto.tvmaze.model.Show
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.data.cassandra.core.selectOne
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.TopicPartition
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ShowListener(
    private val showRepository: ShowRepository,
    private val cassandraTemplate: CassandraTemplate
) {
    //@RabbitListener(queues = [queueName])
    @KafkaListener(groupId = "show-seeder", topicPartitions = [
        TopicPartition(topic = "show", partitions = ["0", "1", "2"])
    ])
    fun receiveShowInfo(cr: ConsumerRecord<UUID, Show>, @Payload show: Show){
        showRepository.save(show.po())
        val query = QueryBuilder.selectFrom("shows")
            .function("max", Selector.column("show_id"))
        val max = cassandraTemplate.selectOne<Long>(query.build())
        println("max : $max")
        println(cr)
        println("received: $show")
    }
}