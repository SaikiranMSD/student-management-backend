FROM maven:3.8-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:11-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.war app.war
EXPOSE 2222
ENV SPRING_PROFILES_ACTIVE=render
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.war"]

