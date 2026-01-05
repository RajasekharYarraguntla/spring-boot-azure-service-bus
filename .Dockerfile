# Use Eclipse Temurin Java 24 as base image
FROM eclipse-temurin:24-jre

# Set working directory
WORKDIR /app

# Copy the built jar into the container
COPY target/spring-boot-azure-service-bus-1.0.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
