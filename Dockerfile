FROM eclipse-temurin:17-jdk

COPY build/libs/nttdata-app-bankTransaction-backend-0.0.1-SNAPSHOT.jar nttdata-account-ws-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","nttdata-account-ws-0.0.1-SNAPSHOT.jar"]