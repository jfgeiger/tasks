import Vue from "vue";
import Vuex from "vuex";
import tasksService from "./tasks.service.ts";
import taskService from "./task.service.ts";

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
        tasks: []
    },
    mutations: {
        setTasks (state, tasks) {
            state.tasks = tasks
        },
        addTask (state, task) {
            state.tasks.push(task)
        },
        updateTask (state, task) {
            const existingTask = state.tasks.find(_ => task.name === _.name);

            if (existingTask) {
                existingTask.closed = task.closed
            }
        },
        closeTask (state, name) {
            const existingTask = state.tasks.find(_ => name === _.name);

            if (existingTask) {
                existingTask.closed = true
            }
        }
    }
});

const tasks = {

    reload() {
        tasksService.read()
            .then(_ => store.commit('setTasks', _.data))
    }
};

const task = {

    add(name) {
        taskService.read(name)
            .then(_ => store.commit('addTask', _.data))
    },

    reload(name) {
        taskService.read(name)
            .then(_ => store.commit('updateTask', _.data))
    },

    close(name) {
        store.commit('closeTask', name)
    }
};

export default {

    store,
    tasks,
    task
};
