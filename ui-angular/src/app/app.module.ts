import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {RouterModule} from "@angular/router";
import {ReactiveFormsModule} from "@angular/forms";

import {AppComponent} from "./app.component";
import {HttpClientModule} from "@angular/common/http";
import {StateService} from "./model/state.service";
import {MatCardModule} from "@angular/material/card";
import {MatIconModule, MatListModule} from "@angular/material";
import {TaskListComponent} from "./task-list/task-list.component";
import {TaskListElementComponent} from "./task-list-element/task-list-element.component";
import {TasksService} from "./model/tasks.service";
import {TaskService} from "./model/task.service";

@NgModule({
    imports: [
        BrowserModule,
        ReactiveFormsModule,
        RouterModule.forRoot([
            {path: '', component: TaskListComponent},
        ]),
        HttpClientModule,
        MatCardModule,
        MatListModule,
        MatIconModule
    ],
    declarations: [
        AppComponent,
        TaskListComponent,
        TaskListElementComponent
    ],
    providers: [
        TasksService,
        TaskService,
        StateService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
