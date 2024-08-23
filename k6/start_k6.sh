#!/bin/sh

VUS=${VUS}
DURATION=${DURATION}

echo "Starting K6 load test with ${VUS} VUs for ${DURATION} duration..."

k6 run --vus ${VUS} --duration ${DURATION} /test_k6.js

#tail -f /dev/null
