FROM maven:3.8-amazoncorretto-17

RUN yum update -y \
    && mkdir /app

COPY src /app/src
COPY pom.xml /app

RUN mvn -f /app/pom.xml clean package -DskipTests

EXPOSE 8084

ENTRYPOINT ["java","-jar","/app/target/flightSearchEngine.jar"]