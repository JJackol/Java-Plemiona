<template>
  <div class="Wioska">
    <pre v-text="$attrs" />
    <h1>Gracz: {{ wioska.name }}</h1>

    <h2>Wioski:</h2>
    <ul>
      <li> {{ wioska.resource }}</li>
      <li>{{ wioska.soldiers }}</li>
      <li>{{ wioska.points }}</li>
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
  data() {},
  computed: {
    ...mapGetters("player", ["player"])
  },
  mounted() {
    store.dispatch("player/fetchPlayer");
  },
  methods: {}
};
</script>
