FROM eclipse-temurin:latest
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]