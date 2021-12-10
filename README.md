# kubernetes-practice


## zookeeper
```bash
# 部署zookeeper
cd zookeeper-cluster/
helm upgrade --install zookeeper-cluster . --cleanup-on-fail

# 检查zookeeper节点的状态
for i in 0 1 2; do kubectl exec -it zookeeper-cluster-1 -c kubernetes-zookeeper -- zkServer.sh status; done
```