<template>
  <v-card class="mx-auto text-center" color="blue-grey" dark max-width="600">
    <v-card-title primary-title>
      <v-row>
        <v-col cols="12" sm="6">
          <v-menu
            v-model="menu"
            :close-on-content-click="false"
            :close-on-click="false"
            :nudge-bottom="50"
            min-width="290px"
            ref="dateMenu"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                v-model="dateRangeText"
                label="Date Range"
                readonly
                v-bind="attrs"
                v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker header-color="blue-grey" v-model="currentDate" @input="change" range>
              <v-spacer></v-spacer>
              <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
              <v-btn text color="primary" @click="saveDateRange(currentDate)">OK</v-btn>
            </v-date-picker>
          </v-menu>
        </v-col>
      </v-row>
    </v-card-title>
    <v-card-text>
      <v-sheet color="rgba(0, 0, 0, .12)">
        <v-sparkline
          auto-draw
          :auto-draw-duration="1000"
          :gradient="gradient"
          :value="chartValue"
          color="rgba(255, 255, 255, .7)"
          height="100"
          padding="24"
          label-size="8"
          stroke-linecap="round"
          smooth
        >
          <template v-slot:label="item">{{ item.value }}￥</template>
        </v-sparkline>
      </v-sheet>
    </v-card-text>

    <v-card-text>
      <div class="display-1 font-weight-thin">Detail in {{getChartDate}} days</div>
    </v-card-text>

    <v-divider></v-divider>

    <v-card-actions class="justify-center">
      <v-btn block text>Go to Report</v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
const gradients = [
  ["#222"],
  ["#42b3f4"],
  ["red", "orange", "yellow"],
  ["purple", "violet"],
  ["#00c6ff", "#F0F", "#FF0"],
  ["#f72047", "#ffd200", "#1feaea"],
];
export default {
  props: ["ReportDialog"],
  data: () => ({
    gradient: gradients[5],
    menu: false,
    chartValue: [],
    defaultDate: [
      new Date(
        new Date().setTime(new Date().getTime() - 6 * 24 * 60 * 60 * 1000)
      )
        .toISOString()
        .substr(0, 10),
      new Date().toISOString().substr(0, 10),
    ],
    currentDate: [
      new Date(
        new Date().setTime(new Date().getTime() - 6 * 24 * 60 * 60 * 1000)
      )
        .toISOString()
        .substr(0, 10),
      new Date().toISOString().substr(0, 10),
    ],
  }),
  computed: {
    dateRangeText() {
      return this.currentDate.join(" ~ ");
    },
    getChartDate() {
      if (this.currentDate.length === 2) {
        return (
          (new Date(this.currentDate[1]) - new Date(this.currentDate[0])) /
            1000 /
            60 /
            60 /
            24 +
          1
        );
      }
      return (
        (new Date(this.defaultDate[1]) - new Date(this.defaultDate[0])) /
          1000 /
          60 /
          60 /
          24 +
        1
      );
    },
  },
  methods: {
    saveDateRange(currentDate) {
      if (currentDate.length === 2) {
        if (currentDate[0] === currentDate[1]) {
          alert("Should not be the same");
          this.currentDate = [currentDate[0]];
        } else {
          this.$refs.dateMenu.save(currentDate);
          this.initChart(currentDate);
        }
      } else {
        alert("Selected at least two item");
      }
    },
    change(date) {
      if (date.length === 2) {
        if (date[0] === date[1]) {
          alert("Should not be the same");
          this.currentDate = [date[0]];
        } else {
          new Date(date[0]) > new Date(date[1])
            ? (this.currentDate = [date[1], date[0]])
            : [date[0], date[1]];
        }
      }
    },
    initChart(dateRange) {
      this.$http
        .post(this.$url.retrieveByExpenseDateRange, dateRange)
        .then((res) => {
          this.chartValue = res;
        });
    },
  },
  mounted() {
    this.initChart(this.currentDate);
  },
  watch: {
    ReportDialog() {
      if (!this.ReportDialog) {
        //slow the UI render speed
        setTimeout(() => {
          this.currentDate = this.defaultDate;
          this.initChart(this.currentDate);
        }, 500);
      }
    },
  },
};
</script>