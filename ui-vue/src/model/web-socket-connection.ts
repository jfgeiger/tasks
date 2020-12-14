import state from "./state.ts";

const onOpenHandler = {

    onOpen() {
        return () => console.log('Open')
    }
};

const onMessageHandler = {

    onMessage() {
        return (event) => {
            const data = JSON.parse(event.data);
            const operation = data.op;
            const name = data.name;

            if (operation === 'create') {
                this.onCreate(name);
            } else if (operation === 'open') {
                this.onOpen(name);
            } else if (operation === 'close') {
                this.onClose(name);
            } else {
                this.onDefault(event);
            }
        };
    },

    onCreate(name) {
        console.log('Task created: ' + name);
        state.task.add(name)
    },

    onOpen(name) {
        console.log('Task opened: ' + name);
        state.task.reload(name)
    },

    onClose(name) {
        console.log('Task closed: ' + name);
        state.task.close(name)
    },

    onDefault(event) {
        console.log('Message received: ' + JSON.stringify(event.data))
    }
};

const onErrorHandler = {

    onError() {
        return (event) => console.log('Error: ' + JSON.stringify(event.error))
    }
};

const onCloseHandler = {

    onClose() {
        return (event) => {
            console.log('Close: ' + JSON.stringify(event.reason) + ' (' + event.code + ')');

            if (!event.wasClean) {
                console.log('Unclean close, reconnect');
                webSocketConnection.initialize()
            }
        }
    }
};

const webSocketConnection = {

    initialize() {
        const webSocket = new WebSocket('ws://localhost:9080/tasks-backend-1.0-SNAPSHOT/ws');
        webSocket.onopen = onOpenHandler.onOpen();
        webSocket.onmessage = onMessageHandler.onMessage();
        webSocket.onerror = onErrorHandler.onError();
        webSocket.onclose = onCloseHandler.onClose();
    }
};

export default webSocketConnection;
