FROM openjdk:17
COPY dossier/target/dossier-1.0-SNAPSHOT.jar dossier-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/dossier-1.0-SNAPSHOT.jar"]