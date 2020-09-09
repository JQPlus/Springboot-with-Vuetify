<template>
  <div>
    <v-file-input
      v-model="files"
      placeholder="Upload your documents"
      label="File input"
      multiple
      prepend-icon="mdi-paperclip"
    >
      <template v-slot:selection="{ text }">
        <v-chip small label color="primary">{{ text }}</v-chip>
      </template>
    </v-file-input>
    <v-btn color="success" @click="uploadFiles">Upload</v-btn>
    <v-btn color="success" @click="downloadFile">Download</v-btn>
    <v-btn color="success" @click="previewFile">Preview</v-btn>
  </div>
</template>

<script>
export default {
  data: () => ({
    files: [],
  }),
  methods: {
    uploadFiles() {
      if (this.files.length > 0) {
        this.$file.upload(this.files, "test").then((res) => {
          console.log(res);
        });
      }
    },
    downloadFile() {
      this.$file.download("test", "test.xlsx");
    },
    previewFile() {
      this.$file.preview("test", "Space.png");
    },
  },
};
</script>