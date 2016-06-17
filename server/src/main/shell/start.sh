#!/usr/bin/env bash
#第一个参数,jar路径
JAR_PATH=$1
if test -z "$JAR_PATH"; then
    JAR_PATH="demo.jar"
fi
java -jar ${JAR_PATH} &
echo "start pid:$!"