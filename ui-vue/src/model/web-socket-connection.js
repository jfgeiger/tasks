"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var state_ts_1 = require("./state.ts");
var onOpenHandler = {
    onOpen: function () {
        return function () { return console.log('Open'); };
    }
};
var onMessageHandler = {
    onMessage: function () {
        var _this = this;
        return function (event) {
            var data = JSON.parse(event.data);
            var operation = data.op;
            var name = data.name;
            if (operation === 'create') {
                _this.onCreate(name);
            }
            else if (operation === 'open') {
                _this.onOpen(name);
            }
            else if (operation === 'close') {
                _this.onClose(name);
            }
            else {
                _this.onDefault(event);
            }
        };
    },
    onCreate: function (name) {
        console.log('Task created: ' + name);
        state_ts_1.default.task.add(name);
    },
    onOpen: function (name) {
        console.log('Task opened: ' + name);
        state_ts_1.default.task.reload(name);
    },
    onClose: function (name) {
        console.log('Task closed: ' + name);
        state_ts_1.default.task.close(name);
    },
    onDefault: function (event) {
        console.log('Message received: ' + JSON.stringify(event.data));
    }
};
var onErrorHandler = {
    onError: function () {
        return function (event) { return console.log('Error: ' + JSON.stringify(event.error)); };
    }
};
var onCloseHandler = {
    onClose: function () {
        return function (event) {
            console.log('Close: ' + JSON.stringify(event.reason) + ' (' + event.code + ')');
            if (!event.wasClean) {
                console.log('Unclean close, reconnect');
                webSocketConnection.initialize();
            }
        };
    }
};
var webSocketConnection = {
    initialize: function () {
        var webSocket = new WebSocket('ws://localhost:9080/tasks-backend-1.0-SNAPSHOT/ws');
        webSocket.onopen = onOpenHandler.onOpen();
        webSocket.onmessage = onMessageHandler.onMessage();
        webSocket.onerror = onErrorHandler.onError();
        webSocket.onclose = onCloseHandler.onClose();
    }
};
exports.default = webSocketConnection;
