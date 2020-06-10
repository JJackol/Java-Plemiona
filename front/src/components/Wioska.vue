<template>
  <div class="Wioska">
    <h1>Wioska: {{ wioska.name }}</h1>
    <p @click="refresh">refresh</p>

    <ul>
      <li>
        Zasoby: {{ wioska.resources }} <span :text="wioska.resources"></span>
        <button @click="mine">Wydobywaj</button>
      </li>
      <li>Wojsko: {{ wioska.soldiers }}</li>
      <li>Punkty: {{ wioska.points }}</li>
    </ul>

    <h2>Budynki</h2>
    <ul>
      <li v-for="item in wioska.buildings" :key="item.id">
        <div>
          <hr />
          {{ item.type }} --- poziom {{ item.level }}
          <button @click="rozbuduj(item)">rozbuduj</button>
          <br />
          Koszt --- {{ item.upgradeCost }}
          <hr />
        </div>
      </li>
      <li>
        Nowy budynek

        <form>
          <select v-model="sel">
            <option v-for="i in typy" :key="i" :value="i">{{ i }}</option>
          </select>
          <input value="Dodaj" type="button" @click="nowa" />
        </form>
      </li>
    </ul>

    <h2>Wojsko</h2>
    <p>W wiosce stacjonuje {{ wioska.soldiers }} żołnierzy</p>
    <p>
      <button @click="recruit">Rekrutuj</button>
      <input
        width="15px"
        v-model="amount"
        type="number"
        id="amount"
        name="amount"
        min="1"
        max="100"
      />
    </p>
  </div>
</template>

<script>
// eslint-disable-next-line no-unused-vars
import store from "../store";
import { mapGetters } from "vuex";
export default {
  name: "Wioska",
  props: {},
  data() {
    return {
      typy: ["mill", "forge", "farm", "Guildhall"],
      sel: "",
      amount: 0
    };
  },
  computed: {
    ...mapGetters("wioska", ["wioska"])
  },
  mounted() {},
  methods: {
    rozbuduj(item) {
      console.log(item);
      store
        .dispatch("wioska/upgrade", item)
        .then(store.dispatch("wioska/fetchWioska", this.wioska.id));
    },
    nowa(item) {
      console.log(item);
      console.log(this.sel);
      store
        .dispatch("wioska/build", this.sel)
        .then(store.dispatch("wioska/fetchWioska", this.wioska.id));
    },
    mine(item) {
      console.log(item);
      store
        .dispatch("wioska/mine", this.wioska)
        .then(store.dispatch("wioska/fetchWioska", this.wioska.id));
    },
    recruit(item) {
      console.log(item);
      store
        .dispatch("wioska/recruit", this.amount)
        .then(store.dispatch("wioska/fetchWioska", this.wioska.id));
    },
    refresh() {
      store.dispatch("wioska/fetchWioska", this.wioska.id);
    }
  }
};
</script>
