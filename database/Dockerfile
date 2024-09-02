FROM mysql:latest

COPY ./start_mysql.sh /docker-entrypoint-initdb.d/

RUN chmod +x /docker-entrypoint-initdb.d/start_mysql.sh

#CMD sleep infinity