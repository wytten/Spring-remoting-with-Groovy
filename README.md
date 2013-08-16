Spring-remoting-with-Groovy
===========================

### Build

    mvn install
    
This will create a web application in spring-remote-server,
and an executable jar in spring-remote-client.
    

### Start server

    (cd spring-remote-server; mvn jetty:run)


### Run client

In another window:

    (cd spring-remote-client; java -jar target/*-exec.jar)
