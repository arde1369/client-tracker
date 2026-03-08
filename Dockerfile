# syntax=docker/dockerfile:1.4
# Multi-stage build for Spring Boot (Java 21)
# 1) Build stage
FROM eclipse-temurin:21-jdk AS builder

# Install Maven (Debian package) so we can build the project
RUN apt-get update && \
    apt-get install -y --no-install-recommends maven && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Ensure the Maven config directory exists
RUN mkdir -p /root/.m2

# Build the local `data-services` dependency, since it is not published to a remote repo
COPY data-services ./data-services
RUN --mount=type=secret,id=maven-settings,target=/root/.m2/settings.xml \
    --mount=type=cache,target=/root/.m2 \
    mvn -s /root/.m2/settings.xml -f /app/data-services/pom.xml -B -q -DskipTests install

# Copy and build the main application
COPY client-tracker ./client-tracker
RUN --mount=type=secret,id=maven-settings,target=/root/.m2/settings.xml \
    --mount=type=cache,target=/root/.m2 \
    mvn -s /root/.m2/settings.xml -f /app/client-tracker/pom.xml -B -q -DskipTests package

# 2) Runtime stage
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Optional: allow profile override at runtime
ENV SPRING_PROFILES_ACTIVE=dev

# Copy finished jar from build stage
COPY --from=builder /app/client-tracker/target/*.jar ./app.jar

# Expose default Spring Boot port
EXPOSE 8080

# Run with profile "dev" by default, but allow override via SPRING_PROFILES_ACTIVE
ENTRYPOINT ["java", "-jar", "/app/app.jar", "--spring.profiles.active=${SPRING_PROFILES_ACTIVE}"]
