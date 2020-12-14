import {ADD, CLOSE, SET_LIST, UPDATE} from "../constants/action-types";
import {initialState} from "./index";

function taskReducer(state = initialState, action) {
    if (action.type === SET_LIST) {
        const tasks = action.payload;
        return Object.assign({}, state, { tasks });
    }

    if (action.type === ADD) {
        const task = action.payload;
        const tasks = state.tasks.concat(task);

        return Object.assign({}, state, { tasks });
    }

    if (action.type === UPDATE) {
        const tasks = state.tasks.map(_ => _);
        const task = action.payload;
        const existingTask = tasks.find(_ => task.name === _.name);

        if (existingTask) {
            existingTask.closed = task.closed
        }

        return Object.assign({}, state, { tasks });
    }

    if (action.type === CLOSE) {
        const tasks = state.tasks.map(_ => _);
        const name = action.payload;
        const existingTask = tasks.find(_ => name === _.name);

        if (existingTask) {
            existingTask.closed = true;
        }

        return Object.assign({}, state, { tasks });
    }

    return state;
}

export default taskReducer;
