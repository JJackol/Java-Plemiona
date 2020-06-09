<template>
  <v-container>
    <div id="container">
      &nbsp;&nbsp;&nbsp;&nbsp;
      <v-form ref="form">
        <div class="textbox">
          <v-text-field
            v-model="login"
            :rules="[rules.required]"
            label="Login"
            required
          ></v-text-field>
        </div>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <div class="textbox">
          <v-text-field
            v-model="password"
            :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="[rules.required, rules.min]"
            :type="show1 ? 'text' : 'password'"
            label="Hasło"
            required
            @click:append="show1 = !show1"
          ></v-text-field>
        </div>
        <div>
          <v-btn
            style="float: right"
            class="white--text"
            color="#a91225"
            rounded
            v-on:click="validate"
            >Zaloguj się</v-btn
          >
        </div>
        <div style="clear: both;"></div>
        <div style="float: right; margin-top: 2%;">
          <a href="forgottenPassword.html"> <u>Nie pamiętam hasła</u> </a>
        </div>
        <div style="clear: both;"></div>
      </v-form>
    </div>
  </v-container>
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
      const payload = {
        username: username,
        password: password
      };
      if (payload.username !== "" && payload.password !== "") {
        store
          .dispatch("context/login", payload)
          .then(() => this.$router.push("home"))
          .catch(e => {
            alert(e);
            // location.reload();
          });
      } else {
        alert("Podano puste dane");
      }
    },
    submit: function() {
      if (this.$refs.form.validate()) {
        console.log(this.login, this.password);
        this.$router.push("home");
      }

      // alert('submited login ='+ this.login+"and password=" + this.password )
      if (this.login === "admin" && this.password === "") {
        alert(
          "submited login = " + this.login + " and password = " + this.password
        );
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
