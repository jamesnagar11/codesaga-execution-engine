<div align="center">

# 🔥 codesaga-execution-engine — Code Execution Engine

**Sandboxed multi-language code execution at scale**  
*Java · C++ · Python · Redis Streams consumer · Docker-isolated · KEDA autoscaled*

[![TypeScript](https://img.shields.io/badge/TypeScript-007ACC?style=for-the-badge&logo=typescript&logoColor=white)](https://www.typescriptlang.org/)
[![Bun](https://img.shields.io/badge/Bun-000000?style=for-the-badge&logo=bun&logoColor=white)](https://bun.sh/)
[![Java](https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![C++](https://img.shields.io/badge/C++-00599C?style=for-the-badge&logo=cplusplus&logoColor=white)](https://isocpp.org/)
[![Redis](https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)](https://redis.io/)
[![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://docker.com/)
[![Kubernetes](https://img.shields.io/badge/Kubernetes-326CE5?style=for-the-badge&logo=kubernetes&logoColor=white)](https://kubernetes.io/)

</div>

---

## 🗺️ Ecosystem Navigation — You Are Here

> This repository is **Module 3 of 5** in the **CodeSaga Distributed System**. Every module is an independent, deployable service. Navigate between them easily:

| Module | Repo | Role | Docker Image |
|--------|------|------|--------------|
| ① Client | [`codesaga`](https://github.com/jamesnagar11/codesaga) | Next.js Client — UI, Auth, Problem Pages | `jamesnagar/codesaga-client` |
| ② Socket Gateway | [`codesaga-websocket-server`](https://github.com/jamesnagar11/codesaga-websocket-server) | WebSocket server, Redis Streams producer, Pub/Sub subscriber | `jamesnagar/codesaga-ws` |
| **③ You are here** | [`codesaga-execution-engine`](https://github.com/jamesnagar11/codesaga-execution-engine) | Sandboxed code runner (Java, C++, Python) | `jamesnagar/codesaga-engine` |
| ④ Bulk DB Executor | [`codesaga-bulk-executor`](https://github.com/jamesnagar11/codesaga-bulk-executor) | Batches up to 100 DB writes in a single SQL statement | `jamesnagar/codesaga-bulk` |
| ⑤ Cron Sweeper | [`codesaga-bulk-master`](https://github.com/jamesnagar11/codesaga-bulk-master) | Auto-claims stale jobs, reconciles Redis memory | `jamesnagar/codesaga-cron` |
| ⚙️ GitOps Config | [`staging-ops`](https://github.com/jamesnagar11/staging-ops) | Kubernetes manifests managed by ArgoCD | — |

---

## 🏗️ Full System Architecture — Interactive Diagram

> **👉 [Open Full Interactive Diagram →](https://jamesnagar11.github.io/codesaga/diagram/)**
>
> *Pan, zoom, shift arrows, hover nodes for details — switch to "③ Exec Engine" tab for this module's specific flow*

<div align="center">

[![Architecture Diagram](https://img.shields.io/badge/🔍_View_Interactive_Diagram-f472b6?style=for-the-badge&logoColor=white)](https://jamesnagar11.github.io/codesaga/diagram/)

</div>

---

### 📐 Full System Overview

```
╔══════════════════════════════════════════════════════════════════════════════╗
║                     ☸  Kubernetes Cluster (k8s)                             ║
║                                                                              ║
║  Users ──► NGINX Ingress ──► ① Next.js + ② Socket Gateway                 ║
║                                     │                                        ║
║                            Redis Stream: events:code                         ║
║                                     │                                        ║
║                            ┌────────▼──────────────────────────────┐        ║
║                            │  ★ ③ Execution Engine Workers (THIS)  │        ║
║                            │  KEDA: scale at Redis lag ≥ 50        │        ║
║                            │                                        │        ║
║                            │  1. xReadGroup from stream             │        ║
║                            │  2. Write code to /JavaFd /CppFd       │        ║
║                            │  3. Compile (javac / g++)              │        ║
║                            │  4. Run vs tester (3.5s TLE)           │        ║
║                            │  5. Publish result → Pub/Sub           │        ║
║                            │  6. xAck + xDel                        │        ║
║                            └────────────────────────────────────────┘        ║
║                                     │ PUBLISH code:result:{srv-uuid}         ║
║                                     ▼                                        ║
║                            ② Socket Server → User (codeResponse)            ║
╚══════════════════════════════════════════════════════════════════════════════╝
```

---

### 🔍 This Module — Execution Pipeline

```
Redis Stream: codesaga:events:code
          │
          │  xReadGroup (blocking, COUNT: 2, BLOCK: 3s)
          ▼
┌───────────────────────────────────────────────────────────────┐
│                 ③ Execution Engine Worker                      │
│                                                                │
│  1. Parse payload: { language, code, problemTitle, ... }       │
│                                                                │
│  2. Write user code to local file directory:                   │
│     Java   → JavaFd/Solution.java                              │
│     C++    → CppFd/Solution.cpp  (auto-includes bits/stdc++.h) │
│     Python → PythonFd/Solution.py                              │
│                                                                │
│  3. Compile (Java: javac, C++: g++, Python: skip)              │
│     → On Compilation Error: publish CE result, return          │
│                                                                │
│  4. Run against problem-specific Tester file                   │
│     (tester file lives in the same /Fd folder per language)    │
│     Timeout: 3500ms → TLE if exceeded                          │
│                                                                │
│  5. Capture stdout / stderr                                    │
│     Verdict: Accepted | Wrong Answer | TLE | CE | Runtime Error │
│                                                                │
│  6. Publish result → Redis Pub/Sub channel:                    │
│     "code:result:{subscriber_id}"                              │
│     (the exact socket server that enqueued the job)            │
│                                                                │
│  7. xAck + xDel the message from the stream                    │
└───────────────────────────────────────────────────────────────┘
          │
          │  Redis Pub/Sub PUBLISH
          ▼
Socket Server → socket.to(socketId).emit('codeResponse')
          │
          ▼
User sees real-time verdict in browser ✅
```

---

## 📋 What This Module Does

`codesaga-execution-engine` is the **heart of the judge system** — the worker that actually compiles and runs user code. It is a stateless, containerized worker that picks jobs from a Redis Stream, executes them in an isolated filesystem directory, runs the code against problem-specific test cases, and publishes the verdict back via Redis Pub/Sub.

> **Security note:** Each worker runs inside its own Docker container. If a user submits malicious code that corrupts the process, only that container is affected — Kubernetes restarts it automatically. The host machine is never touched.

---

## 🔐 Sandbox Security Model

| Threat | Mitigation |
|--------|-----------|
| Infinite loops / hangs | 3.5 second hard execution timeout |
| Host filesystem access | Code runs inside Docker container; no host volume mounted |
| Network exploitation | Worker container has no outbound network to user data |
| Container corruption | Kubernetes `restartPolicy: Always` — pod auto-heals |
| Resource exhaustion | k8s `resources.limits` enforces CPU/memory caps per pod |

---

## 📊 Performance & Scale Metrics

| Scenario | Without Scaling | With Scaling |
|----------|----------------|--------------|
| 1,000 simultaneous submissions | ~4–5 minutes (single process, 4 cores) | **~12 seconds** (50 parallel workers) |
| Autoscaling trigger | — | KEDA: Redis Stream lag ≥ 50 messages |
| Time per job | ~1 second (compile + run) | Same — but parallelized across 50 workers |
| Worker crash recovery | Service down | Kubernetes restarts pod in seconds |
| Malicious code impact | Could corrupt host | **Isolated to one container** |

**The math:**
```
1,000 jobs ÷ 50 workers = 20 jobs per worker
20 jobs × 1 sec each = 20 seconds total
(with 4 CPU cores per worker: ~12 seconds effectively)

vs. single worker: 1,000 × 1s = 1,000 seconds (17 minutes)
Improvement: 98% reduction in processing time
```

---

## 🛠️ Tech Stack

| Component | Technology |
|-----------|-----------|
| Language | TypeScript (Bun runtime) |
| Compilers | OpenJDK 21 (`javac`/`java`), GCC/G++ (`g++`), Python 3 |
| Message Queue | Redis Streams (consumer group pattern) |
| Pub/Sub | Redis Pub/Sub (result publishing) |
| Process Execution | Node.js `child_process` (`exec`, `spawn`) |
| Container | Docker (oven/bun:1 base + apt-installed compilers) |
| Autoscaling | KEDA (Redis Stream lag scaler, trigger: lag ≥ 50) |

---

## 📁 Project Structure

```
execution-engine/
├── src/
│   ├── index.ts         # Worker loop: xReadGroup → process → xAck/xDel
│   ├── processor.ts     # Core: language dispatch, compile, run, publish
│   ├── config/
│   │   └── redis.ts     # Redis client + publisher setup
│   ├── lib/
│   │   └── types.ts     # MessageType interface
│   └── utils/
│       ├── init.ts      # xGroupCreate initialization
│       ├── process.ts   # executeCommand, executeWithTimeout helpers
│       ├── publisher.ts # publishResult to Redis Pub/Sub
│       └── store.ts     # Problem title → tester file mapping
├── JavaFd/              # Java tester files + Solution.java (written at runtime)
├── CppFd/               # C++ tester files + Solution.cpp (written at runtime)
├── Dockerfile
└── .env
```

---

## ⚙️ Local Setup

### Prerequisites
- [Bun](https://bun.sh/) runtime (`curl -fsSL https://bun.sh/install | bash`)
- Java 21+ (`sudo apt install openjdk-21-jdk`)
- G++ (`sudo apt install g++`)
- Python 3 (`sudo apt install python3`)
- Redis running locally

---

### Method 1 — Manual Installation

```bash
# 1. Clone the repository
git clone https://github.com/jamesnagar11/codesaga-execution-engine.git
cd codesaga-execution-engine

# 2. Install dependencies
bun install

# 3. Create your .env file
cp .env.example .env   # then fill in the values below

# 4. Build
bun run build

# 5. Start the worker
bun run start
```

The worker will begin polling the Redis stream for jobs immediately.

---

### Method 2 — Docker (Build Locally)

```bash
docker build -t codesaga-engine .

docker run -d \
  -e REDIS_URL=redis://localhost:6379 \
  -e STREAM_KEY=codesaga:events:code \
  -e CONSUMER_GROUP=india-1 \
  codesaga-engine
```

> The Dockerfile installs OpenJDK 21 and g++ so you **don't need them locally** — Docker handles the entire runtime.

---

### Method 3 — Docker (Pre-built Image from DockerHub) ⚡ Fastest

```bash
docker run -d \
  -e REDIS_URL=redis://localhost:6379 \
  -e STREAM_KEY=codesaga:events:code \
  -e CONSUMER_GROUP=india-1 \
  jamesnagar/codesaga-engine:latest
```

Scale horizontally — just run more containers pointing to the same Redis:
```bash
# Run 5 parallel workers
for i in {1..5}; do
  docker run -d \
    -e REDIS_URL=redis://your-redis:6379 \
    -e STREAM_KEY=codesaga:events:code \
    -e CONSUMER_GROUP=india-1 \
    jamesnagar/codesaga-engine:latest
done
```

---

### Method 4 — Run Full Platform (All 5 Services)

> See the [full Docker Compose setup in the main client repo →](https://github.com/jamesnagar11/codesaga#method-4--run-full-platform-all-5-services-with-docker-compose)

---

## 🌍 Environment Variables Reference

| Variable | Required | Default | Description |
|----------|----------|---------|-------------|
| `REDIS_URL` | ✅ | `redis://localhost:6379` | Redis connection URL (supports `rediss://` for TLS) |
| `STREAM_KEY` | ✅ | `codesaga:events:code` | Redis Stream key to consume from |
| `CONSUMER_GROUP` | ✅ | `india-1` | Redis consumer group name |

---

## 🚀 Kubernetes / GitOps Deployment

This project uses a **fully declarative GitOps workflow**:

1. Push to `main` → GitHub Actions builds & pushes `jamesnagar/codesaga-engine:{sha}` to DockerHub
2. GitHub Actions patches `staging-ops/staging/codesaga/execution-engine/manifest.yaml` with the new tag
3. ArgoCD detects the diff and auto-syncs — **zero manual steps**

```yaml
# From the GitHub Actions CI/CD (cd.yml):
# Automatically updates the image tag in staging-ops on every push to main
sed -i 's|image: jamesnagar/codesaga-engine:.*|image: jamesnagar/codesaga-engine:${{ github.sha }}|' \
  staging/codesaga/execution-engine/manifest.yaml
```

To explore Kubernetes manifests, KEDA ScaledObjects, and ArgoCD Applications:

> 👉 **[staging-ops repo →](https://github.com/jamesnagar11/staging-ops)**

---

<div align="center">

**Built with ❤️ by [James Nagar](https://github.com/jamesnagar11)**  
*Part of the CodeSaga distributed platform — 5 microservices, 1 mission*

</div>
