<template>
  <div class="Wioska">
    <pre v-text="$attrs" />

    <h1>Wioska: {{ wioska.name }}</h1>
    <p>opis</p>

    <ul>
      <li>Zasoby: {{ wioska.resources }}</li>
      <li>Wojsko: {{ wioska.soldiers }}</li>
      <li>Punkty: {{ wioska.points }}</li>
    </ul>
  </div>
</template>

<script>
// eslint-disable-next-line no-unused-vars
import store from "../store";
import { mapGetters } from "vuex";
export default {
  name: "Wioska",
  props: {},
  data() {return {}},
  computed: {
    ...mapGetters("wioska", ["wioska"])
  },
  mounted() {
    store.dispatch("wioska/fetchWioska");
  },
  methods: {}
};
</script>
