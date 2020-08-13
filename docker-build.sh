#!/usr/bin/env bash
mvn clean package
docker build -t oklimenko/processing:1.0.12 .
docker tag oklimenko/processing:1.0.12 oklimenko/processing
#docker push oklimenko/processing:1.0.0

#docker save oklimenko/processing:1.0.11 | (eval $(minikube docker-env) && docker load)


#docker tag ubuntu $(minikube ip -p minikube):5000/ubuntu