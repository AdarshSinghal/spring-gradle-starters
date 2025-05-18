#!/bin/sh

APP_IMAGE="java-app:latest"

docker compose down

# Delete existing image if it exists
docker image inspect "$APP_IMAGE" > /dev/null 2>&1 && docker rmi -f "$APP_IMAGE"

# Build the new image
docker buildx build --platform linux/amd64 -t "$APP_IMAGE" --load .

docker compose up -d