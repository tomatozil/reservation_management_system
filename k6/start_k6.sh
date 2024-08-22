#!/bin/bash

echo "Creating a new K6 test script..."
docker run --rm -i -v $PWD:/app -w /app grafana/k6 new test_k6.js

VUS=${VUS}
DURATION=${DURATION}

echo "Starting K6 load test with ${VUS} VUs for ${DURATION} duration..."

k6 run --vus $VUS --duration $DURATION - < /app/test_k6.js

