import http from "k6/http";
import { check, sleep } from "k6";

export const options = {
  vus: 10,
  duration: "10s",
  thresholds: {
    http_req_duration: ["p(95)<1000"],
    http_req_failed: ["rate<0.01"],
  },
};

export default function () {
  const baseUrl = __ENV.BASE_URL || "http://localhost:8080";
  const res = http.get(baseUrl);

  check(res, {
    "status is 200": (r) => r.status === 200,
    "response time is under 1000ms": (r) => r.timings.duration < 1000,
  });

  sleep(1);
}
