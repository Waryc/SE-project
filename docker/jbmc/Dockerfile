FROM ubuntu:22.04

# 安装依赖
RUN apt-get update && apt-get install -y \
    cmake \
    gcc \
    g++ \
    git \
    libgmp-dev \
    maven \
    openjdk-17-jdk \
    wget \
    unzip \
    && rm -rf /var/lib/apt/lists/*

# 下载 CBMC 发布版本而不是克隆仓库
RUN wget https://github.com/diffblue/cbmc/archive/refs/heads/develop.zip -O cbmc.zip \
    && unzip cbmc.zip \
    && cd cbmc-develop \
    && cmake -S . -B build -DWITH_JBMC=ON \
    && cd build \
    && make -j$(nproc) \
    && cp src/jbmc/jbmc /usr/local/bin/ \
    && cd / \
    && rm -rf cbmc.zip cbmc-develop

WORKDIR /workdir