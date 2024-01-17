#!/bin/bash
set -x

# K6_OPTS='--http-debug="full"'
K6_OPTS=''
k6 run $K6_OPTS index.js
