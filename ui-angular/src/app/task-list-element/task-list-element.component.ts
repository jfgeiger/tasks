import {Component, Input} from "@angular/core";
import {Task} from "src/app/model/task.model";


@Component({
    selector: 'app-task-list-element',
    templateUrl: './task-list-element.component.html',
    styleUrls: ['./task-list-element.component.css']
})
export class TaskListElementComponent {

    @Input()
    public task: Task;
}
