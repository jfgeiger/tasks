import React from "react";

class TaskListElement extends React.Component {
    render() {
        return (
            <li>
                <b>{this.props.task.name}</b>
            </li>
        )
    }
}

export default TaskListElement;
