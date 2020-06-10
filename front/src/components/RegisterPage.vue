<template>
  <div id="container">
    <row>
      <h3>Login</h3>
      <input v-model="login" type="text" />
    </row>
    <row>
      <h3>E-mail</h3>
      <input v-model="email" type="email" />
    </row>
    <row>
      <h3>Hasło</h3>
      <input v-model="password" type="password" />
    </row>
    <br />
    <row>
      <button @click="validate">Zaloguj</button>
    </row>
  </div>
</template>

<script>
import store from "../store/index";
// import { mapGetters } from "vuex";

export default {
  name: "LoginPage",
  data() {
    return {
      show1: false,
      password: "",
      login: "",
      email: "",
      rules: {
        required: value => !!value || "Wymagane.",
        min: v => v.length >= 8 || "Minimum 8 znaków"
      }
    };
  },
  props: {},
  computed: {
    // ...mapGetters("context", ["user"])
  },
  methods: {
    validate() {
      const username = this.login;
      const password = this.password;
      const email = this.email;
      const payload = {
        username: username,
        email: email,
        password: password
      };
      if (payload.username !== "" && payload.password !== "") {
        store
          .dispatch("context/register", payload)
          .then(() => alert("logged"))
          .catch(e => {
            alert(e);
            // location.reload();
          });
      } else {
        alert("Podano puste dane");
      }
    }
  }
};
</script>

<style scoped>
body {
  margin: 0;
  background-color: white;
  font-family: sans-serif;
  font-size: 12px;
  color: #9c9c9a;
}
#container {
  margin: 0;
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 28%;
}
</style>
