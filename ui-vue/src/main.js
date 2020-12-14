import Vue from "vue";
import App from "./App.vue";
import state from "./model/state.ts";
import webSocketConnection from "./model/web-socket-connection.ts";

Vue.config.productionTip = false;

state.tasks.reload();
webSocketConnection.initialize();

const store = state.store;

new Vue({
    render: h => h(App),
    store
}).$mount('#app');
