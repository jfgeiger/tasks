import {BehaviorSubject, Observable} from "rxjs/index";
import {Injectable} from "@angular/core";
import {Task} from "./task.model";
import {TasksService} from "./tasks.service";
import {TaskService} from "./task.service";


@Injectable({
    providedIn: 'root',
})
export class StateService {

    private readonly tasksSubject: BehaviorSubject<Task[]> = new BehaviorSubject([]);

    public readonly tasks: Observable<Task[]> = this.tasksSubject.asObservable();

    private webSocket: WebSocket;

    constructor(private tasksService: TasksService, private taskService: TaskService) {
        this.reloadTasks();
        //this.initializeWebSocket();
    }

    private initializeWebSocket(): void {
        this.webSocket = new WebSocket("ws://localhost:9080/tasks-backend-1.0-SNAPSHOT/ws");
        this.webSocket.onopen = this.onOpen();
        this.webSocket.onmessage = this.onMessage();
        this.webSocket.onerror = this.onError();
        this.webSocket.onclose = this.onClose();
    }

    private onOpen() {
        return () => console.log("Open");
    }

    private onMessage() {
        return (event: MessageEvent) => {
            const data = JSON.parse(event.data);
            const operation = data.op;
            const name = data.name;

            if (operation === 'create') {
                console.log("Task created: " + name);
                this.loadTask(name);
            } else if (operation === 'open') {
                console.log("Task opened: " + name);
                this.reloadTask(name);
            } else if (operation === 'close') {
                console.log("Task closed: " + name);
                this.closeTask(name);
            } else {
                console.log("Message received: " + JSON.stringify(event.data));
            }
        }
    }

    private onError() {
        return (event: ErrorEvent) => console.log("Error: " + JSON.stringify(event.error));
    }

    private onClose() {
        return (event: CloseEvent) => {
            console.log("Close: " + JSON.stringify(event.reason) + " (" + event.code + ")");

            if (!event.wasClean) {
                console.log("Unclean close, reconnect");
                //this.initializeWebSocket();
            }
        }
    }

    private reloadTasks() {
        this.tasksService.get()
            .subscribe(_ => this.tasksSubject.next(_));
    }

    private loadTask(name: string) {
        this.taskService.get(name)
            .subscribe(_ => {
                const updatedTasks: Task[] = this.tasksSubject.value.map(__ => __);
                updatedTasks.push(_);
                this.tasksSubject.next(updatedTasks);
            });
    }

    private reloadTask(name: string) {
        this.taskService.get(name)
            .subscribe(_ => {
                const updatedTasks: Task[] = this.tasksSubject.value.map(__ => __);
                const task: Task = updatedTasks.find(__ => _.name === __.name);

                if (task) {
                    task.closed = _.closed;
                    this.tasksSubject.next(updatedTasks);
                }
            });
    }

    private closeTask(name: string) {
        const updatedTasks: Task[] = this.tasksSubject.value.map(_ => _);
        const task: Task = updatedTasks.find(_ => name === _.name);

        if (task) {
            task.closed = true;
            this.tasksSubject.next(updatedTasks);
        }
    }
}
