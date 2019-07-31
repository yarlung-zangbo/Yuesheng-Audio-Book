FROM openjdk:8-alpine
VOLUME /tmp
#ADD target/tv-2.0.jar apptv.jar
#ADD res.txt res.txt
RUN apk add gcc-c++ && export PATH=$PATH:/usr/bin
RUN wget http://ffmpeg.org/releases/ffmpeg-4.1.4.tar.bz2
RUN tar -jxvf ffmpeg-4.1.4.tar.bz2
RUN wget -O yasm-1.3.0.tar.gz https://www.tortall.net/projects/yasm/releases/yasm-1.3.0.tar.gz && \
   tar xzvf yasm-1.3.0.tar.gz && \
   cd yasm-1.3.0 && \
   ./configure --prefix="$HOME/ffmpeg_build" --bindir="$HOME/bin" && \
   make && \
   make install
WORKDIR /ffmpeg-4.1.4/
RUN ./configure --enable-shared --prefix=/usr/local/ffmpeg
RUN make && make install && cd /usr/local/ffmpeg && ./ffmpeg -version
EXPOSE 8085
ENTRYPOINT ["java","-jar","/apptv.jar"]
