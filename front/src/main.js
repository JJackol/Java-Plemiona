import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import axios from "./plugins/axios";
import vuetify from "./plugins/vuetify";

Vue.config.productionTip = false;
axios.defaults.baseURL = "http://localhost:8080";

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount("#app");
