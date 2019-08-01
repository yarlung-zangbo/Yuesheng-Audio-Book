FROM openjdk:8-alpine
VOLUME /tmp
ADD target/tv-2.0.jar apptv.jar
ADD res.txt res.txt
ADD log4j.properties log4j.properties
RUN apk update
RUN apk add yasm && apk add ffmpeg && ffmpeg -version
EXPOSE 8085
ENTRYPOINT ["java","-jar","/apptv.jar"]
