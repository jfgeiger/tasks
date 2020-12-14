#!/bin/bash
curl -v -H "Content-Type: application/json" --data "{\"name\":\""$1"\"}" http://localhost:9080/tasks-backend-1.0-SNAPSHOT/api/tasks
