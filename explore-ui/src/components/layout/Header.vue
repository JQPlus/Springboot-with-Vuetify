<template>
  <v-app-bar app clipped-left clipped-right color="blue-grey" dark dense>
    <v-app-bar-nav-icon @click="controlLeftNavigation"></v-app-bar-nav-icon>
    <v-toolbar-title>Toolbar</v-toolbar-title>
    <v-spacer></v-spacer>
    <v-btn icon color="blue-grey" @click="controlRightNavigation">
      <img src="@/assets/icon/idea.svg" />
    </v-btn>
  </v-app-bar>
</template>

<script>
export default {
  data: () => ({}),
  methods: {
    controlLeftNavigation() {
      this.$emit("controlLeftNavigation");
    },
    controlRightNavigation() {
      this.$emit("controlRightNavigation");
    }
  }
};
</script>