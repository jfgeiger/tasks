<template>
    <li>
        <b>{{task.name}}</b>
    </li>
</template>
<script>
    export default {
        name: 'TaskListElement',
        props: {
            task: Object
        }
    }
</script>
