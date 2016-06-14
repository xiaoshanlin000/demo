#!/usr/bin/env bash
kill $(ps aux | grep 'demo.jar' | awk '{print $2}')