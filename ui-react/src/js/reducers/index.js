import taskReducer from "./task-reducer";
import {TASK} from "../constants/entities";

const initialState = {
    tasks: []
};

function rootReducer(state = initialState, action) {
    if (action.entity === TASK) {
        return taskReducer(state, action);
    }

    return state;
}

export {
    initialState,
    rootReducer
};
