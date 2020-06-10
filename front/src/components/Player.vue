<template>
  <div class="Wioska">
    <pre v-text="$attrs" />
    <h1>Gracz: {{ player.username }}</h1>
    {{ player }}
    <h2>Wioski:</h2>
    <ul>
      <li v-for="item in villages" :key="item.id" @click="view_wioska(item)">
        {{ item.name }}
      </li>
    </ul>
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
    return {};
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
    }
  }
};
</script>
