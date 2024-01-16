import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    thresholds: {
        // Assert that 99% of requests finish within time.
        http_req_duration: ["p(99) < 5000"],
      },
      // Ramp the number of virtual users up and down
      stages: [
        { duration: "30s", target: 1 },
        { duration: "1m",  target: 10 },
        { duration: "30s", target: 0 },
      ]
}

const getEnv = (name, defaultValue) => {
    const value = __ENV[name];
    if (value === undefined) {
        return defaultValue;
    }
    return value;
}

const baseURL = () => {
    return getEnv('K6_BASE_URL', 'http://localhost:3030');
}

const request = (path) => {
    return '' + baseURL() + path;
} 

const sleepAfterRequest = () => {
    sleep(1);
}

export default function () {
    const rand_x = (scale) => {
        let _scale = scale || 100;
        return 1 + Math.floor(Math.random() * _scale);
    }
    var reqs = [
        () => { return { method: 'GET', url: request('/') } },
        () => { return { method: 'GET', url: request('/factorial?x='+rand_x(1000)) } },
        () => { return { method: 'GET', url: request('/fibo/recursive?x='+rand_x(10)) } },
        () => { return { method: 'GET', url: request('/fibo/iterative?x='+rand_x(10)) } },
        () => { return { method: 'GET', url: request('/sort?x='+rand_x(100)) } }
        // () => { return { method: 'GET', url: request('/fibo/sleep?x=1') } },
    ];
    reqs.forEach(reqf => {
        let req = reqf();
        let res = http.request(req.method, req.url);
        check(res, {
            "is status 200": (r) => r.status === 200,
        });
        sleepAfterRequest();
    });
}