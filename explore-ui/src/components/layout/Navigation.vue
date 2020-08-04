<template>
  <div>
    <!-- left nav -->
    <v-navigation-drawer
      v-model="drawer"
      :color="color"
      :expand-on-hover="expandOnHover"
      :mini-variant.sync="miniVariant"
      :right="right"
      :permanent="permanent"
      :src="bg"
      clipped
      dark
      app
    >
      <v-list dense nav class="py-0">
        <v-list-item two-line :class="miniVariant && 'px-0'">
          <v-list-item-avatar>
            <img src="https://randomuser.me/api/portraits/men/81.jpg" />
          </v-list-item-avatar>
          <v-list-item-content>
            <v-list-item-title>Application</v-list-item-title>
            <v-list-item-subtitle>Subtext</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <v-divider></v-divider>

        <v-list-item v-for="item in items" :key="item.title" link :to="item.to">
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <!-- right nav -->
    <v-navigation-drawer v-model="rightDrawer" :right="!right" color="white" light app clipped>
      <v-list dense nav class="py-0"></v-list>
      <template v-slot:append>
        <div class="pa-2">
          <v-btn block>Logout</v-btn>
        </div>
      </template>
    </v-navigation-drawer>
  </div>
</template>
<script>
export default {
  data() {
    return {
      drawer: true,
      rightDrawer: false,
      items: [
        { title: "Dashboard", icon: "mdi-view-dashboard", to: "/" },
        { title: "NoteBook", icon: "mdi-image", to: "notebook" },
        { title: "About", icon: "mdi-help-box", to: "about" },
      ],
      color: "blue-grey",
      colors: ["primary", "blue", "success", "red", "teal"],
      right: false,
      permanent: false,
      miniVariant: true,
      expandOnHover: true,
      background: false,
    };
  },
  computed: {
    //background src if attr 'background' is true
    bg() {
      return this.background
        ? "https://cdn.vuetifyjs.com/images/backgrounds/bg-2.jpg"
        : undefined;
    },
  },
};
</script>