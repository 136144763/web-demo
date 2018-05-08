#!/usr/bin/env bash

source ~/.profile

mvn clean package -Dmaven.test.skip=true
scp target/*.jar root@192.168.50.46:/root/web-demo/web-demo.jar