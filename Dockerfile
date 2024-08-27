FROM alpine:3.18

RUN apk update &&  \
    apk upgrade &&  \
    apk add --no-cache  \
    openjdk17 \
    dumb-init

ARG JAR_FILE=core/build/libs/*.jar
COPY ${JAR_FILE} /purchase.jar

COPY ./start-application.sh /start-application.sh
RUN chmod 755 /start-application.sh

ENTRYPOINT ["dumb-init", "--", "/start-application.sh"]

#CMD sleep infinity