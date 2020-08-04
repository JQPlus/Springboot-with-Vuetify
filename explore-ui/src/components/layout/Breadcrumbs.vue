<template>
  <v-system-bar>
    <v-breadcrumbs :items="items">
      <template v-slot:item="{ item }">
        <v-breadcrumbs-item
          :href="item.href"
          :disabled="item.disabled"
        >{{ item.text.toUpperCase() }}</v-breadcrumbs-item>
      </template>
    </v-breadcrumbs>
  </v-system-bar>
</template>
<script>
export default {
  data: () => ({
    items: [
      {
        text: "Dashboard",
        disabled: false,
        href: "/"
      },
      {
        text: "Link 1",
        disabled: false,
        href: "photos"
      },
      {
        text: "Link 2",
        disabled: false,
        href: "about"
      }
    ]
  })
};
</script>