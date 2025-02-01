FROM openjdk:17
COPY gateway/target/gateway-1.0-SNAPSHOT.jar gateway-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/gateway-1.0-SNAPSHOT.jar"]