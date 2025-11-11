- Install Prometheus in cluster (e.g., kube-prometheus-stack Helm chart).
- Ensure it scrapes the app via annotations:
  prometheus.io/scrape: "true"
  prometheus.io/port: "8080"
  prometheus.io/path: "/actuator/prometheus"
- In Grafana, import dashboard ID 4701 (Spring Boot / Micrometer) or similar.