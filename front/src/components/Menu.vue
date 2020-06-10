<template>
  <div id="container">
    <div id="aboveMenu">
      <div id="user">
        <!--        <ul id="usernameAndLogout">-->
        <!--          <li id="logout"><a @click="wyloguj">WYLOGUJ SIĘ</a></li>-->
        <!--        </ul>-->
      </div>
    </div>

    <div class="nav">
      <ol>
        <li><a href="/player">Gracz</a></li>
        <li><a href="/wioska">Wioska</a></li>
        <li>
          <a href="/reports">&nbsp;Reporty</a>
        </li>
        <li><a @click="wyloguj">WYLOGUJ SIĘ</a></li>
      </ol>
    </div>
  </div>
</template>

<script>
import store from "../store";

export default {
  name: "Menu",
  components: {},
  methods: {
    wyloguj() {
      store
        .dispatch("context/logout")
        .then(() => this.$router.push("login"))
        .catch(e => {
          alert(e);
          // location.reload();
        });
    }
  }
};
</script>

<style scoped>
body {
  margin: 0;
  background-color: #fff;
  //font-family: 'Nirmala UI', sans-serif;
  font-family: sans-serif;
  padding: 0;
}
.nav {
  background-color: #001005;
  text-align: center;
  margin: 0;
  width: 100%;
  padding: 0;
  display: flex;
  justify-content: center;
  //height: 90px;
}
ol {
  padding: 0;
  margin: 0;
  list-style-type: none;
  font-size: 22px;
  //height: 100%;
  display: inline-block;
  letter-spacing: 0px;
  width: 90%;
}
ol a {
  color: #fff;
  text-decoration: none;
  display: block;
  padding: 10% 0;
  margin: 0;
  //height: 100%;
}
ol > li {
  float: left;
  width: 19%;
  //height: 100%;
  padding: 0;
  margin-left: 3%;
  margin-right: 3%;

  display: block;
  //width: 18%;
  //margin-left: 3%;
  //margin-right: 3%;
}
ol > li:hover {
  background-color: #1a2336;
  height: 100%;
}
ol > li:hover > ul {
  display: block;
}
ol > li > ul {
  list-style-type: none;
  padding: 0;
  margin: 0;
  height: 0px;
  display: none;
}
ol > li > ul > li {
  background-color: #0a8836;
  position: relative;
  z-index: 100;
  border-top: 1px solid #fff;
}
ol > li > ul > li:first-child {
  border-top: none;
}
#logo {
  margin-left: 7%;
  //margin-top: 2%;
  width: 19%;
  display: inline-block;
}
#logoImg {
  margin-top: 12%;
  margin-bottom: 11%;
}
#user {
  display: inline-block;
  position: absolute;
  right: 7%;
  padding-top: 2.9%;
}

#aboveMenu {
  display: flex;
}
#usernameAndLogout {
  font-size: 22px;
  letter-spacing: 0px;
  color: #292828;
  list-style-type: none;
  margin: 0;
  display: flex;
  padding: 0;
}
#username {
  padding-right: 13px;
  border-right: 1px solid #292828;
}
#logout {
  padding-left: 13px;
  font-weight: 700;
}
#logout a {
  color: #292828;
  text-decoration: none;
}
#logout a:hover {
  color: #5c5b5b;
  text-decoration: none;
}
</style>
