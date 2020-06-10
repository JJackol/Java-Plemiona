import axios from "axios";

const store = {
  namespaced: true,
  state: {
    reports: [],
  },
  getters: {
    reports(state) {
      return state.reports;
    }
  },
  mutations: {
    setReports(state, reports) {
      state.reports = reports;
    }
  },
  actions: {
    async fetchReports({ commit }) {
      // eslint-disable-next-line no-unused-vars
      let id = 1;
      return new Promise((resolve, reject) => {
        axios
          .get("/api/village/playerBattleReports/")
          .then(response => {
            commit("setReports", response.data);
            console.log(response.data);
            resolve();
          })
          .catch(e => {
            reject(e);
          });
      });
    }

  }

};

export default store;
