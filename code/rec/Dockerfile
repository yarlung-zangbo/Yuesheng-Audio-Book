FROM openjdk:8-alpine
VOLUME /tmp
ADD target/cf-1.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]