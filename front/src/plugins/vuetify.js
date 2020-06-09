import Vue from "vue";
import Vuetify from "vuetify/lib";

Vue.use(Vuetify);

const options = {
  theme: {
    primary: "#325323",
    secondary: "#8AC32E",
    accent: "#D8EBF1",
    info: "#5B5C79"
  },
  iconfont: "mdi"
};

export default new Vuetify(options);
