#stage1
FROM maven:3.9.11-eclipse-temurin-21 AS myBuild

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

#stage2
FROM eclipse-temurin:21-jre

WORKDIR /fapp

COPY --from=myBuild /app/target/*.jar /fapp/app.jar

ENTRYPOINT ["java","-jar","/fapp/app.jar"]