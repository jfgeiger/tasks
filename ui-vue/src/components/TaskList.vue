<template>
    <div>
        <p>Open</p>
        <ul>
            <task-list-element v-for="task in openTasks" :key="task.name" v-bind:task="task"></task-list-element>
        </ul>

        <p>Closed</p>
        <ul>
            <task-list-element v-for="task in closedTasks" :key="task.name" v-bind:task="task"></task-list-element>
        </ul>
    </div>
</template>
<script>
    import TaskListElement from './TaskListElement.vue'

    export default {
        name: 'TaskList',
        components: {
            TaskListElement
        },
        computed: {
            openTasks: function () {
                return this.$store.state.tasks.filter(_ => _.closed === false)
            },
            closedTasks: function () {
                return this.$store.state.tasks.filter(_ => _.closed === true)
            }
        }
    }
</script>
