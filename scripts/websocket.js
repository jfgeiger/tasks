var ws = new WebSocket("ws://localhost:9080/tasks-backend-1.0-SNAPSHOT/ws");
ws.onopen = function() { console.log("Open"); };
ws.onmessage = function(event) { console.log("Message received: " + JSON.stringify(event.data)); };
ws.onerror = function(event) { console.log("Error: " + event.data); };
ws.onclose = function(event) { console.log("Close: " + event.reason + " (" + event.code + ")"); };
