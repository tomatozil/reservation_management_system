FROM grafana/k6:0.46.0

USER root

RUN apk update &&  \
    apk upgrade &&  \
    apk add --no-cache  \
    dumb-init

COPY ./start_k6.sh /start_k6.sh
COPY ./test_k6.js /test_k6.js

RUN chmod 755 /start_k6.sh

ENTRYPOINT ["/usr/bin/dumb-init", "--", "/start_k6.sh"]

#CMD sleep infinity
