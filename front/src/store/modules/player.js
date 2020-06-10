import axios from "axios";

const store = {
  namespaced: true,
  state: {
    player: {},
    villages: []
  },
  getters: {
    player(state) {
      return state.player;
    },
    villages: state => state.villages
  },
  mutations: {
    setPlayer(state, player) {
      state.player = player;
      state.player.villages = [];
      state.villages = player.village;
    },
    setVillages(state, villages) {
      state.player.villages = villages;
      state.villages = villages;
    }
  },
  actions: {
    async fetchPlayer({ commit, dispatch }) {
      let id = 1;
      return new Promise((resolve, reject) => {
        axios
          .get("/api/user/" + id)
          .then(response => {
            commit("setPlayer", response.data);
            console.log(response.data);
            resolve();
          })
          .then(() => dispatch("fetchPlayerVillages"))

          .catch(e => {
            reject(e);
          });
      });
    },
    async fetchPlayerVillages({ commit }) {
      let id = 1;
      return new Promise((resolve, reject) => {
        axios
          .get("/api/village/playerVillages/" + id)
          .then(response => {
            commit("setVillages", response.data);
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
