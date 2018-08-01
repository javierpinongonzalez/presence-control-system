#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null && pwd )"
version="0.0.1"

cd "$DIR" && cd ../

mvn clean

mvn package

docker build -t presence-control-system:"$version" -f docker/Dockerfile .

cd docker

docker-compose stop && docker-compose rm -y

docker-compose up -d presence-control-system-db

sleep 5

docker-compose up -d presence-control-system


