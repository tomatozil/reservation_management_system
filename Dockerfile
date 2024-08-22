FROM alpine:3.18

RUN apk update &&  \
    apk upgrade &&  \
    apk add --no-cache  \
    openjdk17

ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk
ENV PATH="$JAVA_HOME/bin:$PATH"

ARG JAR_FILE=core/build/libs/*.jar
COPY ${JAR_FILE} /purchase.jar
ENTRYPOINT ["java","-jar","/purchase.jar"]

#CMD sleep infinity