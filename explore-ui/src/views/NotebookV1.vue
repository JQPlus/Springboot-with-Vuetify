<template>
  <v-sheet elevation="3">
    <v-container>
      <v-row :align="'center'">
        <v-col cols="3">
          <!-- basic month select -->
          <v-date-picker
            v-model="monthModel"
            type="month"
            no-title
            scrollable
            color="primary"
            elevation="4"
            full-width
            @change="changeMonth"
          ></v-date-picker>
        </v-col>
        <v-col cols="9">
          <!-- basic day select-->
          <v-item-group v-model="dateModel" @change="changeDate">
            <v-container>
              <v-row dense>
                <v-col v-for="(day,index) in this.dates" :key="index" cols="1">
                  <v-item v-slot:default="{ active, toggle }">
                    <v-card :color="active ? 'blue-grey' : ''" light height="50" @click="toggle">
                      <v-card-text>{{day.date}}</v-card-text>
                    </v-card>
                  </v-item>
                </v-col>
              </v-row>
            </v-container>
          </v-item-group>
        </v-col>
      </v-row>
    </v-container>
    <v-expand-transition>
      <v-sheet v-if="dateModel != null" color="grey lighten-4" height="200" tile>
        <notebookTable :ExpenseDate="this.fullDateTime" />
      </v-sheet>
    </v-expand-transition>
  </v-sheet>
</template>
<script>
import notebookTable from "@/views/Notebook.vue";
export default {
  components: {
    notebookTable,
  },
  data: () => ({
    monthModel: null,
    dateModel: null,
    currentYear: new Date().getFullYear(),
    currentMonth: new Date().getMonth() + 1,
    selectedYear: null,
    selectedMonth: null,
    fullDateTime: null,
    dates: [],
  }),
  methods: {
    changeMonth(dateTime) {
      this.monthModel = dateTime;
      this.selectedYear = parseInt(dateTime.slice(0, 4));
      this.selectedMonth = parseInt(dateTime.slice(5, 7));
      this.dateModel =
        this.selectedMonth === this.currentMonth
          ? new Date().getDate() - 1
          : null;
      this.combineDate(this.selectedYear, this.selectedMonth);
      this.outputFullDate(
        this.selectedYear,
        this.selectedMonth,
        new Date().getDate()
      );
    },
    changeDate(date) {
      let selectedDate = date + 1;
      this.outputFullDate(this.selectedYear, this.selectedMonth, selectedDate);
    },
    combineDate(year, month) {
      this.dates = [];
      let flag = true;
      // get day count of the month
      let count = this.$tools.getMonthDaysCount(year, month);
      let initDate = 1;
      while (flag) {
        this.dates.push({
          detail: `${year}-${month}-${initDate}`,
          date: initDate,
        });
        initDate++;
        if (initDate > count) flag = false;
      }
    },
    outputFullDate(year, month, date) {
      this.fullDateTime = `${year}-${month}-${date}`;
    },
  },
  mounted() {
    //init date count
    this.changeMonth(new Date().toISOString().substr(0, 7));
  },
};
</script>