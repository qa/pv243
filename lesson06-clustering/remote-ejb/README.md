Build Client and Server
-----------------------

mvn clean install


Start Both Servers
------------------

./bin/standalone.sh -c standalone-ha.xml -Djboss.node.name=server1
./bin/standalone.sh -c standalone-ha.xml -Djboss.node.name=server2 -Djboss.socket.binding.port-offset=100


Run the Client
--------------

cd cliend-side
mvn exec:java -Dexec.class=cz.muni.fi.pv243.lesson6.ejb.remote.client.StatelessRemoteClient
mvn exec:java -Dexec.class=cz.muni.fi.pv243.lesson6.ejb.remote.client.StatefulRemoteClient

(Don't forget to rerun 'mvn clean install' when you make changes to the code.)
