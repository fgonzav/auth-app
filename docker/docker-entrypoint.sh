#!/bin/sh

cd /opt/app || exit

JAR=$(ls auth-service*.jar)

java -jar "$JAR" --spring.profiles.active=dev
