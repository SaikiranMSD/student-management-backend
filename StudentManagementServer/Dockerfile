FROM maven:3.8-openjdk-8 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.war app.war
EXPOSE 8761
ENV SPRING_PROFILES_ACTIVE=render
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.war"]

