#!/usr/bin/env bash

until printf "" 2>>/dev/null >>/dev/tcp/cassandra/9042; do
    sleep 5;
    echo "Waiting for cassandra...";
done

echo "executing init script"
cqlsh cassandra -u cassandra -p cassandra -f /scripts/init.cql
