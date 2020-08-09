<template>
  <v-card class="mx-auto" max-width="450" height="200">
    <v-form ref="form">
      <v-container fluid>
        <v-text-field v-model="account" :rules="rules" label="Account" required></v-text-field>
        <v-text-field
          :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
          :rules="rules"
          :type="showPassword ? 'text' : 'password'"
          label="Visible"
          hint="At least 8 characters"
          :value="password"
          class="input-group--focused"
          @click:append="showPassword = !showPassword"
        ></v-text-field>
        <div style="float:right">
          <v-btn color="error" class="mr-4" @click="reset">Reset Form</v-btn>
          <v-btn color="warning" @click="resetValidation">Reset Validation</v-btn>
        </div>
      </v-container>
    </v-form>
  </v-card>
</template>
<script>
export default {
  data() {
    return {
      showPassword: false,
      account: "",
      password: "",
      rules: [
        (v) => !!v || "Name is required",
        // (v) => (v && v.length <= 10) || "Name must be less than 10 characters",
      ],
    };
  },
  methods: {
    validate() {
      this.$refs.form.validate();
    },
    reset() {
      this.account = null;
      this.password = null;
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
  },
  mounted() {
    this.account = "";
    this.password = "";
    this.resetValidation();
  },
};
</script>

<style scoped>
.mx-auto {
  margin-top: 7%;
}
</style>