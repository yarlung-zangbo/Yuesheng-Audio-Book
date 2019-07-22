FROM java:8
VOLUME /tmp
ADD target/eureka-1.0-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]
