<template>
  <div class="Wioska">
    <h1>Raporty</h1>
    <ul>
      <li v-for="item in reporty" :key="item.id" @click="view_wioska(item)">
        {{ item.name }} {{item.id}}
        {{item}}
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
    return {  };
  },
  computed: {
    ...mapGetters("reports", ["reports"])
  },
  mounted() {
    store.dispatch("reports/fetchReports");
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
