# Task Tracker — Quickstart

## Local (no Docker)
1. Start MySQL 8 locally (user: root, pass: password). Create DB `taskdb` or rely on auto create.
2. `mvn spring-boot:run`
3. Visit `http://localhost:8080/tasks`.

## Docker Compose
1. `docker compose up -d --build`
2. `curl http://localhost:8080/tasks`

## Kubernetes
1. Build & push the Docker image to Docker Hub (`ci.yml` does this on pushes to main).
2. Update `image:` in `k8s/app-deployment.yaml` with your Docker Hub username.
3. `kubectl apply -f k8s/` (namespace first), then port-forward or use NodePort 30080.

## Git & GitHub

```bash
git init
git add .
git commit -m "init: spring boot task tracker"
git branch -M main
git remote add origin https://github.com/<you>/task-tracker.git
git push -u origin main

# create a feature branch and experiment
git checkout -b feat/partial-update

# make changes, then
git commit -am "feat: PATCH endpoint improvements"
git push -u origin feat/partial-update

# open a Pull Request on GitHub
```

## API Endpoints

* `GET  http://localhost:8080/tasks`
* `POST http://localhost:8080/tasks`
  ```json
  {
    "title": "Write report",
    "description": "Finish lab notes",
    "status": "IN_PROGRESS"
  }
  ```
* `GET  http://localhost:8080/tasks/1`
* `PUT  http://localhost:8080/tasks/1` (send full object)
* `PATCH http://localhost:8080/tasks/1` (send partial fields)
* `DELETE http://localhost:8080/tasks/1`
* Monitoring endpoint: `GET http://localhost:8080/actuator/prometheus`

## Notes

* Replace `YOUR_DOCKERHUB_USERNAME` and add `DOCKERHUB_USERNAME`/`DOCKERHUB_TOKEN` GitHub secrets for CI.
* For a persistent MySQL in K8s, swap `emptyDir` with a PVC.
* If your lab network blocks NodePort, use `kubectl port-forward` instead.