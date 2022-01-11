# kubernetes-practice


## zookeeper-cluster

```bash
# 部署zookeeper-cluster
cd zookeeper-cluster/
helm upgrade --install zookeeper-cluster . --cleanup-on-fail

# 检查zookeeper节点的状态
for i in 0 1 2; do kubectl exec -it zookeeper-cluster-${i} -c kubernetes-zookeeper -- zkServer.sh status; done

# 连接zookeeper
kubectl exec -it zookeeper-cluster-0 -c kubernetes-zookeeper -- zkCli.sh -server zookeeper-cluster:2181
```

## kafka-signle

```bash
# 部署kafka单节点版
cd kafka-signle/
helm upgrade --install kafka-signle . --cleanup-on-fail

# 验证, 进入容器内部
kubectl exec -it {podName} -- bash
cd opt/kafka/bin

# 发送者
kafka-console-producer.sh --broker-list kafka-signle:9092 --topic topic-test

# 消费者
kafka-console-consumer.sh --bootstrap-server kafka-signle:9092 --topic topic-test --from-beginning

# 查看topic列表
kafka-topics.sh --list --zookeeper zookeeper-cluster:2181
```


## kafka-cluster
```bash
# 部署kafka集群版
cd kafka-cluster/
helm upgrade --install kafka-cluster . --cleanup-on-fail

# 创建topic
kafka-topics.sh --create --topic demo-aaa --partitions 3 --replication-factor 2 --zookeeper zookeeper-cluster:2181/kafka-cluster

# 消费者
kafka-console-consumer.sh --topic demo-aaa --bootstrap-server kafka-cluster:9093
# 生产者
kafka-console-producer.sh --topic demo-aaa --broker-list kafka-cluster:9093

# 管理brokers
zookeeper-shell.sh zookeeper-cluster:2181 ls /brokers/ids
zookeeper-shell.sh zookeeper-cluster:2181 delete /brokers/ids/1
```