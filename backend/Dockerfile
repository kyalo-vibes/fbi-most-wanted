# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file to the container
COPY target/fbi-most-wanted-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]