#!/usr/bin/env bash
#的一个参数,需要停止的名字
STOP_NAME=$1
if test -z "$STOP_NAME"; then
    STOP_NAME="demo.jar"
fi
kill $(ps aux | grep ${STOP_NAME} | awk '{print $2}')