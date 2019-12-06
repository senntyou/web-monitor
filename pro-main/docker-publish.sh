#!/bin/bash

if [ -z "$DOCKER_USERNAME" ]; then
  echo "\$DOCKER_USERNAME required"
  exit 1
fi

VERSION=$(ls build/libs | sed 's/.*webmonitor-//' | sed 's/.jar$//')

echo "webmonitor version: $VERSION"

#echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker build --build-arg JAR_FILE=build/libs/webmonitor-$VERSION.jar -t $DOCKER_USERNAME/webmonitor -t $DOCKER_USERNAME/webmonitor:$VERSION .
docker images
docker push $DOCKER_USERNAME/webmonitor
