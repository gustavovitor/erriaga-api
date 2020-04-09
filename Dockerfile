FROM maven:3.6.3-jdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn package -DskipTests && mv /app/target/*.jar /app/target/app.jar

FROM openjdk:11
COPY --from=build /app/target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]