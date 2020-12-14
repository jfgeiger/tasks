import React from "react";
import TaskListElement from "./TaskListElement";
import {connect} from "react-redux";

const mapStateToProps = state => {
    return {
        openTasks: state.tasks.filter(_ => _.closed === false),
        closedTasks: state.tasks.filter(_ => _.closed === true)
    };
};

class TaskListComponent extends React.Component {
    render() {
        return (
            <div>
                <p>Open</p>
                <ul>
                    {
                        this.props.openTasks.map(task => {
                            return <TaskListElement key={task.name} task={task}/>
                        })
                    }
                </ul>

                <p>Closed</p>
                <ul>
                    {
                        this.props.closedTasks.map(task => {
                            return <TaskListElement key={task.name} task={task}/>
                        })
                    }
                </ul>
            </div>
        )
    }
}

const TaskList = connect(mapStateToProps)(TaskListComponent);

export default TaskList;
