FROM openjdk:17
COPY statement/target/statement-1.0-SNAPSHOT.jar statement-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/statement-1.0-SNAPSHOT.jar"]