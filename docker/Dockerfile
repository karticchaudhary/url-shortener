# ====== Build stage ======
FROM maven:3.9-eclipse-temurin-21-alpine AS build

WORKDIR /app

# Copy pom and resolve deps first (better layer cache)
COPY ../pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline

# Copy source and build
COPY ../src ./src
RUN mvn -q -DskipTests package

# ====== Runtime stage ======
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy the fat jar from build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar app.jar"]