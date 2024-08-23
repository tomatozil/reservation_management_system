FROM grafana/k6:0.46.0

USER root

RUN apk update &&  \
    apk upgrade &&  \
    apk add --no-cache  \
    tini

COPY ./start_k6.sh /app/start_k6.sh

RUN chmod 755 /app/start_k6.sh

ENTRYPOINT ["/sbin/tini", "--", "/app/start_k6.sh"]

#CMD sleep infinity
