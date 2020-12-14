"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var tasks_service_ts_1 = require("./tasks.service.ts");
var axios_1 = require("axios");
var url = tasks_service_ts_1.default.URL;
var httpClient = axios_1.default.create();
var taskService = {
    read: function (name) {
        return httpClient.get(url + "/" + name);
    }
};
exports.default = taskService;
