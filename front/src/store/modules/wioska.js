import axios from "../../plugins/axios";
// eslint-disable-next-line no-unused-vars
import context from "./context";

const store = {
  namespaced: true,
  state: {
    wioska: {},
    wioski: [],
    wynik: {}
  },
  getters: {
    wioska: state => state.wioska,
    wioski: state => state.wioski,
    wynik: state => state.wynik
  },
  mutations: {
    setWioska(state, wioska) {
      state.wioska = wioska;
    },
    setWioski(state, wioski) {
      state.wioski = wioski;
    },
    setWynik(state, wynik) {
      state.wynik = wynik;
    }
  },
  actions: {
    // eslint-disable-next-line no-unused-vars
    async fetchWioska({ commit, getters }, id) {
      // let id = context.getters.getUser(context.state).id;
      console.log("in fetchWioska id:" + id);
      return new Promise((resolve, reject) => {
        axios
          .get("/api/village/" + id)
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
    // eslint-disable-next-line no-unused-vars
    }, async fetchWioski({ commit, getters }) {
      // let id = context.getters.getUser(context.state).id;
      console.log("in fetchWioski");
      return new Promise((resolve, reject) => {
        axios
          .get("/api/village/")
          .then(response => {
            console.log("response");
            console.log(response);
            if (response.status === 200) {
              commit("setWioski", response.data);
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
    },
    // eslint-disable-next-line no-unused-vars
    async upgrade({ commit, getters }, payload) {
      console.log(payload);
      let id = context.getters.getUser(context.state).id;
      let villageid = getters.wioska.id;
      let buildingid = payload.id;
      return new Promise((resolve, reject) => {
        axios
          .post(
            "/api/building/upgrade?playerid=" +
              id +
              "&villageid=" +
              villageid +
              "&buildingid=" +
              buildingid
          )
          .then(response => {
            console.log(response);
            if (response.status === 200) {
              resolve();
            } else {
              reject();
            }
          })
          .catch(() => reject("bad req"));
      });
    },
    // eslint-disable-next-line no-unused-vars
    async build({ commit, getters }, payload) {
      console.log(payload);
      let id = context.getters.getUser(context.state).id;
      let villageid = getters.wioska.id;
      let buildingType = payload;
      return new Promise((resolve, reject) => {
        axios
          .post(
            "/api/building/build?playerid=" +
              id +
              "&villageid=" +
              villageid +
              "&buildingType=" +
              buildingType
          )
          .then(response => {
            console.log(response);
            if (response.status === 200) {
              resolve();
            } else {
              reject();
            }
          })
          .catch(() => reject("bad req"));
      });
    },
    // eslint-disable-next-line no-unused-vars
    async mine({ commit, getters }, payload) {
      console.log(payload);
      let id = context.getters.getUser(context.state).id;
      let villageid = getters.wioska.id;
      return new Promise((resolve, reject) => {
        axios
          .post("/resources/mine/?playerid=" + id + "&villageid=" + villageid)

          .then(response => {
            console.log(response);
            if (response.status === 200) {
              resolve();
            } else {
              reject();
            }
          })
          .catch(() => reject("bad req"));
      });
    },
    // eslint-disable-next-line no-unused-vars
    async recruit({ commit, getters }, payload) {
      console.log(payload);
      let id = context.getters.getUser(context.state).id;
      let villageid = getters.wioska.id;
      let amount = payload;
      return new Promise((resolve, reject) => {
        axios
          .post(
            "/resources/recruit/?playerid=" +
              id +
              "&villageid=" +
              villageid +
              "&amount=" +
              amount
          )
          .then(() => this.fetchWioska({ commit, getters }, getters.wioska.id))
          .then(response => {
            console.log(response);
            if (response.status === 200) {
              resolve();
            } else {
              reject();
            }
          })
          .catch(() => reject("bad req"));
      });
    },
    // eslint-disable-next-line no-unused-vars
    async attack({ commit, getters }, payload) {
      console.log(payload);
      let id = context.getters.getUser(context.state).id;
      let villageid = getters.wioska.id;
      let defId = payload.defid;
      let amount = payload.armySize;

      return new Promise((resolve, reject) => {
        axios
          .post(
            "/api/village/attack/" +
              defId +
              "?playerid=" +
              id +
              "&villageid=" +
              villageid +
              "&armySize=" +
              amount
          )
          .then(response => {
            console.log(response);
            if (response.status === 200) {
              commit("setWynik", response.data);
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
