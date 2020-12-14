import {Component} from "@angular/core";
import {Observable} from "rxjs/index";
import {Task} from "../model/task.model";
import {StateService} from "../model/state.service";
import {map} from "rxjs/internal/operators";


@Component({
    selector: 'app-task-list',
    templateUrl: './task-list.component.html',
    styleUrls: ['./task-list.component.css']
})
export class TaskListComponent {

    private readonly tasks: Observable<Task[]> = this.stateService.tasks;

    public readonly openTasks: Observable<Task[]> = this.tasks
        .pipe(map((tasks: Task[]) => tasks.filter(task => task.closed === false)));

    public readonly closedTasks: Observable<Task[]> = this.tasks
        .pipe(map((tasks: Task[]) => tasks.filter(task => task.closed === true)));

    constructor(private stateService: StateService) {
    }
}
