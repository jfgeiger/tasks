import tasksService from "./tasks.service.ts";
import axios from "axios";

const url = tasksService.URL;
const httpClient = axios.create();

const taskService = {

    read(name) {
        return httpClient.get(`${url}/${name}`);
    }
};

export default taskService;
