<template>
  <div class="Wioska">
    <h1>Gracz: {{ player.username }}</h1>
    <h2>Wioski:</h2>
    <ul>
      <li v-for="item in villages" :key="item.id" @click="view_wioska(item)">
        {{ item.name }} {{item.id}}
      </li>
    </ul>
    <div>
      <h2>Stwórz wioskę</h2>
      <form>
        <input v-model="nowa_nazwa" type="text" />
        <input value="dodaj" type="button" @click="nowa" />
      </form>
    </div>
    {{ player }}
  </div>
</template>

<script>
// eslint-disable-next-line no-unused-vars
import store from "../store";
import { mapGetters } from "vuex";
export default {
  name: "Player",
  props: {},
  data() {
    return { nowa_nazwa: "" };
  },
  computed: {
    ...mapGetters("player", ["player", "villages"])
  },
  mounted() {
    store.dispatch("player/fetchPlayer");
  },
  methods: {
    view_wioska(item) {
      store
        .dispatch("wioska/fetchWioska", item.id)
        .then(() => this.$router.push("wioska"));
    },
    nowa() {
      if (this.nowa_nazwa) {
        store
          .dispatch("wioska/nowaWioska", { new_name: this.nowa_nazwa })
          .then(() => setTimeout(store.dispatch("player/fetchPlayer"),500));
      }
    }
  }
};
</script>
