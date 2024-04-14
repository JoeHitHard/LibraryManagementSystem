#!/bin/sh

cd ./lms || exit
./gradlew clean build publishToMavenLocal
cd ..


cd ./auth-service || exit
./gradlew clean build
cd ..

cd ./book-service || exit
./gradlew clean build
cd ..

cd ./lend-service || exit
./gradlew clean build
cd ..


docker-compose build
docker-compose up -d
