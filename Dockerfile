FROM openjdk:17-jdk-slim
COPY target/meal-recommendation-service.jar app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]