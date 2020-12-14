import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/index";
import {Task} from "./task.model";
import {TasksService} from "./tasks.service";


@Injectable({
    providedIn: 'root',
})
export class TaskService {

    constructor(private httpClient: HttpClient) {
    }

    public get(name: string): Observable<Task> {
        this.httpClient.post('', null, {params: {}});

        return this.httpClient.get<Task>(`${TasksService.URL}/${name}`);
    }
}
