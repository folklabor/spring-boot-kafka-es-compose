# ==> zookeeper
# To have launchd start zookeeper now and restart at login:
#  brew services start zookeeper
# Or, if you don't want/need a background service you can just run:
#  zkServer start

# ==> kafka
# To have launchd start kafka now and restart at login:
#  brew services start kafka
# Or, if you don't want/need a background service you can just run:
#  zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties & kafka-server-start /usr/local/etc/kafka/server.properties

# stop kafka
# kafka-server-stop

# delete topic
#


#create a topic
# kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test

# list topics
# kafka-topics --list --zookeeper localhost:2181

#produce a message in topic test
#kafka-console-producer --broker-list localhost:9092 --topic test
# > your message

#consume messages in topic test
#kafka-console-consumer --bootstrap-server localhost:9092 --topic test --from-beginning