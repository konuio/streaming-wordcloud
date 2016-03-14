Streaming Wordcloud
===
Setup
---
Add OAuth keys to `twitter-search/src/main/resources/twitter4j.properties`.

Zookeeper

```
brew install zookeeper
zookeeper-server-start.sh /usr/local/etc/kafka/zookeeper.properties
```

Kafka

```
brew install kafka
kafka-server-start.sh /usr/local/etc/kafka/server.properties

# Create topic
kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test

# Consume messages
kafka-console-consumer.sh --zookeeper localhost:2181 --topic test --from-beginning
```

Spark

```
brew install apache-spark
SPARK_MASTER_IP=127.0.0.1 start-master.sh
start-slave.sh spark://localhost:7077

# Visit web UI
http://localhost:8080

# Connect to the Spark shell
MASTER=spark://localhost:7077 spark-shell

# Stop master
stop-master.sh

# Start socket text stream
nc -lk 9999
```
