import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/index";
import {Task} from "./task.model";


@Injectable({
    providedIn: 'root',
})
export class TasksService {

    static readonly URL: string = "tasks";

    constructor(private httpClient: HttpClient) {
    }

    public get(): Observable<Task[]> {
        return this.httpClient.get<Task[]>(TasksService.URL);
    }
}
