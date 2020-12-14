"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var axios_1 = require("axios");
var URL = 'tasks';
var httpClient = axios_1.default.create();
var tasksService = {
    URL: URL,
    read: function () {
        return httpClient.get(URL);
    }
};
exports.default = tasksService;
