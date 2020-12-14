"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var vue_1 = require("vue");
var vuex_1 = require("vuex");
var tasks_service_ts_1 = require("./tasks.service.ts");
var task_service_ts_1 = require("./task.service.ts");
vue_1.default.use(vuex_1.default);
var store = new vuex_1.default.Store({
    state: {
        tasks: []
    },
    mutations: {
        setTasks: function (state, tasks) {
            state.tasks = tasks;
        },
        addTask: function (state, task) {
            state.tasks.push(task);
        },
        updateTask: function (state, task) {
            var existingTask = state.tasks.find(function (_) { return task.name === _.name; });
            if (existingTask) {
                existingTask.closed = task.closed;
            }
        },
        closeTask: function (state, name) {
            var existingTask = state.tasks.find(function (_) { return name === _.name; });
            if (existingTask) {
                existingTask.closed = true;
            }
        }
    }
});
var tasks = {
    reload: function () {
        tasks_service_ts_1.default.read()
            .then(function (_) { return store.commit('setTasks', _.data); });
    }
};
var task = {
    add: function (name) {
        task_service_ts_1.default.read(name)
            .then(function (_) { return store.commit('addTask', _.data); });
    },
    reload: function (name) {
        task_service_ts_1.default.read(name)
            .then(function (_) { return store.commit('updateTask', _.data); });
    },
    close: function (name) {
        store.commit('closeTask', name);
    }
};
exports.default = {
    store: store,
    tasks: tasks,
    task: task
};
