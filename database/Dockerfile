FROM alpine:3.18

RUN apk update &&  \
    apk upgrade &&  \
    apk add --no-cache  \
    mysql \
    mysql-client \
    dumb-init

RUN sed -i 's/skip-networking/#skip-networking/g' /etc/my.cnf.d/mariadb-server.cnf
RUN sed -i 's/#bind-address=0.0.0.0/bind-address=0.0.0.0/g' /etc/my.cnf.d/mariadb-server.cnf

COPY ./start_mysql.sh /start_mysql.sh

RUN chmod 755 /start_mysql.sh

ENTRYPOINT ["/usr/bin/dumb-init", "--", "/start_mysql.sh"]

#CMD sleep infinity