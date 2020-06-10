import axios from "../../plugins/axios";
// eslint-disable-next-line no-unused-vars
import context from "./context";

const store = {
  namespaced: true,
  state: {
    wioska: {}
  },
  getters: {
    wioska: state => state.wioska
  },
  mutations: {
    setWioska(state, wioska) {
      state.wioska = wioska;
    }
  },
  actions: {
    // eslint-disable-next-line no-unused-vars
    async fetchWioska({ commit, getters }) {
      let id = context.getters.getUser(context.state).id;
      console.log("in fetchWioska user:" + id);
      return new Promise((resolve, reject) => {
        axios
          .get("/api/village/" + id )
          .then(response => {
            console.log(response);
            if (response.status === 200) {
              commit("setWioska", response.data);
              resolve();
            } else {
              reject();
            }
          })

          .catch(() => reject("bad req"));
      });
    },
    // eslint-disable-next-line no-unused-vars
    async nowaWioska({ commit, getters }, payload) {
      let id = context.getters.getUser(context.state).id;
      let nazwa = payload.new_name;
      console.log("in nowaWioska user:" + id);
      return new Promise((resolve, reject) => {
        axios
          .post("/api/village/" + id + "?villageName=" + nazwa)
          .then(response => {
            console.log(response);
            if (response.status === 200) {
              commit("setWioska", response.data);
              resolve();
            } else {
              reject();
            }
          })

          .catch(() => reject("bad req"));
      });
    }
  }
};

export default store;
