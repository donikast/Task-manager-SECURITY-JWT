FROM eclipse-temurin:21
COPY target/Task-manager-16042025-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
