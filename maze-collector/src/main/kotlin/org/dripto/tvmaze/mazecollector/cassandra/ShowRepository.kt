package org.dripto.tvmaze.mazecollector.cassandra

import org.springframework.data.cassandra.repository.CassandraRepository

interface ShowRepository : CassandraRepository<ShowPO, Long>