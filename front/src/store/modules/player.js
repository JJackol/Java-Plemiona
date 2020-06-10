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
      state.villages = player.village;
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
