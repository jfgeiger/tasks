import axios from "axios";
import tasksService from "./tasks-service";

const url = tasksService.URL;
const httpClient = axios.create();

const taskService = {

    read(name) {
        return httpClient.get(`${url}/${name}`);
    }
};

export default taskService;
