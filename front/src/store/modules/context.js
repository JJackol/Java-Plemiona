import axios from "../../plugins/axios";

const store = {
  namespaced: true,
  state: {
    user: {
      username: "",
      email: "",
      id: 1
    },
    authorization_token: localStorage.getItem("authorization_token") || ""
  },
  getters: {
    getUser: state => state.user,
    is_logged_in: state => state.authorization_token !== ""
  },
  mutations: {
      // eslint-disable-next-line no-unused-vars
    setUser(state, payload) {},
    setAuthorizationToken(state, authorizationToken) {
      state.authorization_token = authorizationToken;
    },
    removeAuthorizationToken(state) {
      state.authorization_token = "";
      localStorage.removeItem("authorization_token");
    }
  },
  actions: {
    async login({ commit }, payload) {
      return new Promise((resolve, reject) => {
        axios
          .post("/api/login/", { ...payload })
          .then(response => {
            console.log(response);
            if (response.status === 200) {
              localStorage.setItem("authorization_token", response.data.token);
              commit("setAuthorizationToken", response.data);
              axios.defaults.headers.common.Authorization = `Token ${response.data.token}`;
              resolve();
            } else {
              reject();
            }
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

export default store;
