#!/usr/bin/env bash

mvn clean package

if [[ ! -f payara-micro.jar ]]; then
  echo "Downloading payara..."
  wget -O payara-micro.jar "https://search.maven.org/remotecontent?filepath=fish/payara/extras/payara-micro/6.2023.2/payara-micro-6.2023.2.jar"
fi

java \
    -Xmx1024m \
    -jar payara-micro.jar \
    --deploy ./target/deploymentissue.war \
    --port 8101

