# Stage 1: Build the JAR (if needed)
FROM amazoncorretto:21-alpine-jdk AS builder
WORKDIR /app

# Copy full project and build
COPY . /app
RUN ./gradlew build -x test --no-daemon || true


# Stage 2: Create a smaller runtime image
FROM bellsoft/liberica-runtime-container:jre-21-crac-cds-slim-musl

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the port that your application listens on
EXPOSE 8090

ENV ENVIRONMENT=prod

# Define the command to run your application
CMD ["java", "-jar", "app.jar", "com.pg.Application"]