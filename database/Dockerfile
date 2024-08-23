FROM alpine:3.18

RUN apk update &&  \
    apk upgrade &&  \
    apk add --no-cache  \
    mysql \
    mysql-client \
    dumb-init

COPY ./start_mysql.sh /start_mysql.sh

RUN chmod 755 /start_mysql.sh

ENTRYPOINT ["/usr/bin/dumb-init", "--", "/start_mysql.sh"]

#CMD sleep infinity