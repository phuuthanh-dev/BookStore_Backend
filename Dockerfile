#FROM openjdk:17-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY target/back-end-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM openjdk:17-jdk-oraclelinux8
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
CMD ["./mvnw", "spring-boot:run"]

#FROM eclipse-temurin:17 as builder
#WORKDIR /opt/app
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
#COPY ./src ./src
#RUN ./mvnw clean install
#
#FROM eclipse-temurin:17
#WORKDIR /opt/app
#EXPOSE 8081
#COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
#ENTRYPOINT ["java", "-jar", "/opt/app/*.jar" ]