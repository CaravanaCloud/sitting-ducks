#!/bin/bash
set -x

K6_OPTS='--http-debug="full"'
k6 run $K6_OPTS index.js
