import axios from "axios";

const URL = 'tasks';
const httpClient = axios.create();

const tasksService = {

    URL,

    read() {
        return httpClient.get(URL);
    }
};

export default tasksService;
