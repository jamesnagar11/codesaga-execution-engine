FROM oven/bun:1

# Install Java (java & javac), C++ (g++), and Python (python3) compilers/interpreters
RUN apt-get update && apt-get install -y --no-install-recommends \
    openjdk-21-jdk-headless \
    g++ \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /usr/src/app

COPY package*.json ./

RUN bun install

COPY . .

RUN bun run build

CMD ["bun", "run", "start"]
