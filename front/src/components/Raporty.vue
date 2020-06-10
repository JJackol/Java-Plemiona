<template>
  <div class="Wioska">
    <h1>Raporty</h1>
    <ul>
      <li v-for="item in villages" :key="item.id" @click="view_wioska(item)">
        {{ item.name }} {{item.id}}
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
    }
  }
};
</script>
