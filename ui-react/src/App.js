import React from "react";
import TaskList from "./js/components/TaskList";
import {setTasks} from "./js/actions/index";
import tasksService from "./js/services/tasks-service";
import webSocketConnection from "./js/services/web-socket-connection";
import store from "./js/store/index";

webSocketConnection.initialize();
tasksService.read()
    .then(_ => store.dispatch(setTasks(_.data)));

class App extends React.Component {
    render() {
        return (
            <div id="app">
                <TaskList />
            </div>
        )
    }
}

export default App;
