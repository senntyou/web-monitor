#!/bin/bash

if [ -z "$DOCKER_USERNAME" ]; then
  echo "\$DOCKER_USERNAME required"
  exit 1
fi

VERSION=$(ls build/libs | sed 's/.*webmonitor-main-//' | sed 's/.jar$//')

echo "webmonitor-main version: $VERSION"

#echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker build --build-arg JAR_FILE=build/libs/webmonitor-main-$VERSION.jar -t $DOCKER_USERNAME/webmonitor-main -t $DOCKER_USERNAME/webmonitor-main:$VERSION .
docker images
docker push $DOCKER_USERNAME/webmonitor-main
