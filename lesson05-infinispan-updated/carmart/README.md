How to run the example
======================

CarMart is a simple web application that uses Infinispan instead of a relational database.
Users can list cars, add new cars or remove them from the CarMart. Information about each car
is stored in a cache. The application also shows cache statistics like stores, hits, retrievals, etc.

The CarMart works in a library mode. All libraries (jar files) are bundled 
with the application and deployed into the server. Caches are configured programatically and run 
in the same JVM as the web application.


Building and deploying to JBoss AS 7
------------------------------------

1) Start JBoss AS 7 where your application will run

    `$JBOSS_HOME/bin/standalone.sh`

2) Build the application

    `mvn clean package`

3) Deploy the application via jboss-as Maven plugin

    `mvn jboss-as:deploy`

4) Go to http://localhost:8080/lesson05

5) Undeploy the application

    `mvn jboss-as:undeploy`