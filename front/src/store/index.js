import Vue from "vue";
import Vuex from "vuex";
import context from "./modules/context";
import wioska from "./modules/wioska";
import player from "./modules/player";
import reports from "./modules/reports";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: { context, wioska, player, reports }
});
