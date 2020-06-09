import axios from "../../plugins/axios";

const context = {
  namespaced: true,
  state: {
    wioska: null
  },
  getters: {
    wioska: state => state.wioska,
  },
  mutations: {
    setWioska(state, wioska) {
      state.wioska = wioska;
    }
  },
  actions: {
    async fetchWioska({ commit }, payload) {
      return new Promise((resolve, reject) => {
        axios
          .post("/api/login_auth/", { ...payload })
          .then(response => {
            if (response.status === 200) {
              localStorage.setItem("authorization_token", response.data.token);
              commit("setAuthorizationToken", response.data);
              axios.defaults.headers.common.Authorization = `Token ${response.data.token}`;
              resolve();
            } else {
              reject();
            }
          })
          .then(() => {
            axios.get("/students/").then(response => {
              console.log(response);
            });
          })

          .catch(() => reject("auth fail"));
      });
    },
    async logout({ commit }) {
      return new Promise((resolve, reject) => {
        axios
          .get("/api/logout")
          .then(() => {
            commit("removeAuthorizationToken");
            console.log(localStorage.getItem("authorization_token"));
            localStorage.removeItem("authorization_token");
            delete axios.defaults.headers.common.Token;
            delete axios.defaults.headers.common.Authorization;
            alert();
            window.location.reload();
          })
          .catch(e => {
            reject(e);
          });
        resolve();
      });
    }
  }
};

export default context;
