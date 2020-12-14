import {TASK} from "../constants/entities";
import {ADD, CLOSE, SET_LIST, UPDATE} from "../constants/action-types";

export function setTasks(payload) {
    return { entity: TASK, type: SET_LIST, payload }
}

export function addTask(payload) {
    return { entity: TASK, type: ADD, payload }
}

export function updateTask(payload) {
    return { entity: TASK, type: UPDATE, payload }
}

export function closeTask(payload) {
    return { entity: TASK, type: CLOSE, payload }
}
