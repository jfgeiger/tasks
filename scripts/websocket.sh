#!/bin/bash
curl \
--include \
--no-buffer \
--header "Connection: Upgrade" \
--header "Upgrade: websocket" \
--header "Sec-WebSocket-Key: SGVsbG8sIHdvcmxkIQ==" \
--header "Sec-WebSocket-Version: 13" \
http://localhost:9080/tasks-backend-1.0-SNAPSHOT/ws
