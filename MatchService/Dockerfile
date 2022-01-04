FROM openjdk:17-alpine

WORKDIR /opt/server
COPY ./target/MatchService-0.0.1-SNAPSHOT.jar server.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "server.jar"]