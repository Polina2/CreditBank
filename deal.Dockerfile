FROM openjdk:17
COPY deal/target/deal-1.0-SNAPSHOT.jar deal-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/deal-1.0-SNAPSHOT.jar"]