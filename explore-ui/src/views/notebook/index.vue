<template>
  <v-container>
    <v-row :align="'center'">
      <v-col cols="3">
        <!-- basic month select -->
        <v-date-picker
          v-model="dateModel"
          type="date"
          scrollable
          elevation="3"
          full-width
          @change="changeMonth"
          :events="functionEvents"
          header-color="blue-grey"
        ></v-date-picker>
      </v-col>
      <v-col cols="9">
        <v-card elevation="3">
          <notebookTable :ExpenseDate="this.dateModel" @getItemInfo="getAllNotebookInfo" />
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import notebookTable from "./notebook_table.vue";
export default {
  components: {
    notebookTable,
  },
  data: () => ({
    dateModel: new Date().toISOString().substr(0, 10),
    notebookInfo: [],
  }),
  methods: {
    getAllNotebookInfo(info) {
      this.notebookInfo = info;
    },
    changeMonth(dateTime) {
      this.dateModel = dateTime;
    },
    functionEvents(date) {
      if (this.notebookInfo.length === 0) {
        setTimeout(() => {}, 10);
      } else {
        let redArr = [];
        let orangeArr = [];
        let greenArr = [];
        let expenseDate;
        this.notebookInfo.forEach((element) => {
          expenseDate = element.expenseDate;
          switch (true) {
            case element.specificExpense > 40:
              redArr.push(expenseDate);
              break;
            case element.specificExpense > 30:
              orangeArr.push(expenseDate);
              break;
            default:
              greenArr.push(expenseDate);
              break;
          }
        });
        let redInc = redArr.includes(date);
        let orgInc = orangeArr.includes(date);
        let greenInc = greenArr.includes(date);
        if (redInc && orgInc) return ["red", "orange"];
        if (redInc) return ["red"];
        if (orgInc) return ["orange"];
        if (greenInc) return ["green"];
        return false;
      }
    },
  },
  mounted() {
    this.changeMonth(new Date().toISOString().substr(0, 10));
  },
};
</script>