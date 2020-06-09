import Vue from "vue";
import Vuex from "vuex";
import context from "./modules/context";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {context}
});
