FROM openjdk:17
COPY calculator/target/calculator-1.0-SNAPSHOT.jar calculator-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/calculator-1.0-SNAPSHOT.jar"]