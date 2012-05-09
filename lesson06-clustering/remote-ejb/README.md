Start servers:
./bin/standalone.sh -c standalone-ha.xml -Djboss.node.name=server1
./bin/standalone.sh -c standalone-ha.xml -Djboss.node.name=server2 -Djboss.socket.binding.port-offset=100

Run mvn exec
mvn install; mvn package
cd cliend-side
mvn3 exec:java -Dexec.class=cz.muni.fi.pv243.lesson6.ejb.remote.client.StatelessRemoteClient
mvn3 exec:java -Dexec.class=cz.muni.fi.pv243.lesson6.ejb.remote.client.StatefulRemoteClient

