import axios from "axios";

const store = {
  namespaced: true,
  state: {
    player: {},
  },
  getters: {
    player(state) {
      return state.player;
    }
  },
  mutations: {
    setPlayer(state, player) {
      state.player = player;
    }
  },
  actions: {
    async fetchPlayer({ commit }) {
      let id = 1;
      return new Promise((resolve, reject) => {
        axios
          .get("/api/user/"+id)
          .then(response => {
            commit("setPlayer", response.data);
            console.log(response);
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
