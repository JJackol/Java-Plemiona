<template>
  <v-container>
    <Menu />
    <v-content>
      <router-view />
    </v-content>
  </v-container>
</template>

<script>
import Menu from "../components/Menu";

export default {
  name: "Layout",
  components: { Menu },
  data: () => ({})
};
</script>
