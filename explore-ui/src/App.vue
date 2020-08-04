<template>
  <v-app>
    <Aside ref="aside" />
    <Header
      ref="header"
      @controlLeftNavigation="leftNavControl"
      @controlRightNavigation="rightNavControl"
    ></Header>
    <v-main>
      <!-- <Bread /> -->
      <v-container fluid>
        <router-view></router-view>
      </v-container>
    </v-main>
    <!-- <v-footer app color="blue-grey" class="white--text">
      <span>Vuetify</span>
      <v-spacer></v-spacer>
      <span>&copy; {{ new Date().getFullYear() }}</span>
    </v-footer>-->
  </v-app>
</template>

<script>
/* eslint-disable */
import Header from "@/components/layout/Header.vue";
import Aside from "@/components/layout/Navigation.vue";
import Bread from "@/components/layout/Breadcrumbs.vue";
export default {
  name: "App",

  components: {
    Header,
    Aside,
    Bread
  },
  data: () => ({
    //
  }),
  methods: {
    leftNavControl() {
      let drawer = this.$refs.aside.drawer;
      // 隐藏&显示菜单
      this.$refs.aside.drawer = !drawer;
    },
    rightNavControl() {
      let drawer = this.$refs.aside.rightDrawer;
      this.$refs.aside.rightDrawer = !drawer;
    }
  }
};
</script>
