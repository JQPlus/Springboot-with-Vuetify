<template>
  <v-data-table
    :headers="headers"
    :items="itemInfo"
    sort-by="expense"
    show-group-by
    :loading="false"
    loading-text="Loading... Please wait"
    dense
    item-key="notebookID"
    height="280"
    :options="{
      page: 1,
      itemsPerPage: 10,
    }"
    :footer-props="{
      showFirstLastPage: true,
      firstIcon: 'mdi-arrow-collapse-left',
      lastIcon: 'mdi-arrow-collapse-right',
      prevIcon: 'mdi-minus',
      nextIcon: 'mdi-plus',
      itemsPerPageOptions: [5, 10, 20, -1],
      itemsPerPageText: '$vuetify.dataFooter.itemsPerPageText',
    }"
  >
    <template v-slot:item.specificExpense="{ item }">
      <v-chip small :color="getColor(item.specificExpense)" dark>{{
        item.specificExpense
      }}</v-chip>
    </template>
    <template v-slot:item.remark="{ item }">
      <v-tooltip bottom>
        <template v-slot:activator="{ on, attrs }">
          <span v-bind="attrs" v-on="on" v-if="item.remark">{{
            customRemark(item.remark)
          }}</span>
        </template>
        <span>{{ item.remark }}</span>
      </v-tooltip>
    </template>
    <!-- slot data-table top to display -->
    <template v-slot:top>
      <v-toolbar flat color="white">
        <!-- <v-toolbar-title>My CRUD</v-toolbar-title> -->
        <v-dialog
          v-model="reportDialog"
          :overlay="false"
          max-width="500px"
          transition="dialog-transition"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn icon v-bind="attrs" v-on="on">
              <v-icon>mdi-file-chart</v-icon>
            </v-btn>
          </template>
          <v-card>
            <report :ReportDialog.sync="reportDialog" />
          </v-card>
        </v-dialog>

        <v-divider inset vertical></v-divider>
        <v-spacer></v-spacer>

        <!-- dialog for CRUD ops -->
        <v-dialog v-model="dialog" max-width="500px">
          <template v-slot:activator="{ on, attrs }">
            <v-btn icon light class="mb-2" v-bind="attrs" v-on="on">
              <v-icon>mdi-plus</v-icon>
            </v-btn>
          </template>
          <v-form v-model="valid" ref="form" :lazy-validation="false">
            <v-card>
              <v-card-title primary-title>
                <span class="headline">{{ formTitle }}</span>
              </v-card-title>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6" md="6" v-if="false">
                    <v-text-field
                      v-model="notebookItem.notebookID"
                      label="Notebook ID"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="6">
                    <v-autocomplete
                      v-model="notebookItem.expenseType"
                      :items="expenseTypeList"
                      item-text="categoryName"
                      item-value="categoryID"
                      label="Expense Type"
                      required
                      :rules="basicRule"
                    ></v-autocomplete>
                  </v-col>
                  <v-col cols="12" sm="6" md="6">
                    <v-text-field
                      v-model="notebookItem.specificExpense"
                      label="Expense"
                      min="0"
                      step="10"
                      required
                      :rules="expenseRule"
                      type="number"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="6">
                    <v-menu
                      v-model="menu"
                      :close-on-content-click="false"
                      min-width="290px"
                    >
                      <template v-slot:activator="{ on, attrs }">
                        <v-text-field
                          v-model="notebookItem.expenseDate"
                          label="Expense Date"
                          :rules="basicRule"
                          required
                          readonly
                          v-bind="attrs"
                          v-on="on"
                        ></v-text-field>
                      </template>
                      <v-date-picker
                        header-color="blue-grey"
                        v-model="notebookItem.expenseDate"
                        @input="menu = false"
                      ></v-date-picker>
                    </v-menu>
                  </v-col>
                  <v-col cols="12" sm="6" md="12">
                    <v-textarea
                      v-model="notebookItem.remark"
                      label="Remark"
                      :rows="textRow"
                      solo
                      counter
                      no-resize
                      clearable
                      prepend-inner-icon="mdi-comment"
                    ></v-textarea>
                  </v-col>
                </v-row>
              </v-container>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
                <v-btn color="blue darken-1" text @click="save">Save</v-btn>
              </v-card-actions>
            </v-card>
          </v-form>
        </v-dialog>
      </v-toolbar>
    </template>
    <template v-slot:item.actions="{ item }">
      <v-icon small class="mr-2" @click="editItem(item)">mdi-pencil</v-icon>
      <v-icon small @click="deleteItem(item)">mdi-delete</v-icon>
    </template>
    <template v-slot:no-data>
      <!-- <v-btn color="primary" @click="initialize">Reset</v-btn> -->
    </template>

    <template v-slot:body.prepend="{ headers }">
      <tr>
        <td :colspan="headers.length">
          <span>Day Expense: {{ dayExpense }}</span>
          <span style="padding-left: 5%"
            >Month Expense: {{ monthExpense }}</span
          >
        </td>
      </tr>
    </template>
  </v-data-table>
</template>
<script>
// import dateFormat from "@/utils/tools.js";
import report from "./notebook_report.vue";
export default {
  components: { report },
  props: ["ExpenseDate"],
  data: () => ({
    textRow: "3",
    valid: false,
    dialog: false,
    reportDialog: false,
    menu: false,
    basicRule: [
      (v) => !!v || "column is empty",
      // v => v.length <= 10 || "Name must be less than 10 characters"
    ],
    expenseRule: [
      (v) => !!v || "error input or column is empty",
      (v) => v >= 0 || "Expense must be larger than 0",
    ],
    headers: [
      // {
      //   text: "Notebook ID",
      //   align: "start",
      //   value: "notebookID",
      // },
      // {
      //   text: "Expense Type",
      //   value: "expenseType",
      //   sortable: false,
      //   groupable: false,
      // },
      {
        text: "Expense Type",
        value: "expenseTypeName",
        sortable: false,
        groupable: false,
      },
      { text: "Expense", value: "specificExpense", groupable: false },
      { text: "Date", value: "expenseDate" },
      { text: "Remark", value: "remark", sortable: false, groupable: false },
      { text: "Actions", value: "actions", sortable: false, groupable: false },
    ],
    itemInfo: [],
    isEdit: false,
    notebookItem: {
      expenseType: "",
      specificExpense: 0,
      expenseDate: new Date().toISOString().substr(0, 10),
      remark: null,
    },
    defaultItem: {
      expenseType: "",
      specificExpense: 0,
      expenseDate: null,
      remark: null,
    },
    expenseTypeList: [],
    dayExpense: 0,
    monthExpense: 0,
    strYearMonth: new Date().toISOString().substr(0, 7),
  }),

  computed: {
    formTitle() {
      return !this.isEdit ? "New Item" : "Edit Item";
    },
  },

  watch: {
    dialog(val) {
      val || this.close();
    },
    ExpenseDate() {
      this.initialize();
      this.notebookItem.expenseDate = this.ExpenseDate;
      this.strYearMonth = this.ExpenseDate.substring(0, 7);
      this.getDayExpense();
    },
    // strYearMonth() {
    //   this.getMonthExpense();
    // },
  },
  beforeCreate() {
    this.$http
      .get(this.$url.getCategoryByTypeCode, {
        categoryCode: "Expense Type",
      })
      .then((res) => {
        this.expenseTypeList = res;
      });
  },
  created() {
    this.initialize();
  },
  mounted() {
    this.getMonthExpense();
    this.getDayExpense();
  },
  methods: {
    validate() {
      this.$refs.form.validate();
    },
    reset() {
      this.$refs.form.reset();
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
    getColor(expense) {
      if (expense > 40) return "red";
      else if (expense > 30) return "orange";
      else return "green";
    },
    customRemark(remark) {
      if (remark.length < 10) {
        return remark;
      }
      return `${remark.slice(0, 10)}...`;
    },
    initialize() {
      this.$http
        .post(this.$url.retrieveByExpenseDate, this.ExpenseDate)
        .then((res) => {
          this.tableLoading = true;
          this.itemInfo = res;
          this.tableLoading = false;
          this.getMonthExpense();
          this.getDayExpense();
          this.$http
            .get(this.$url.retrieveDailyExpense, {
              strYearMonth: this.strYearMonth,
            })
            .then((res) => {
              this.$emit("getItemInfo", res);
            });
        });
    },

    editItem(item) {
      this.isEdit = true;
      // cooy item to {} for notebookItem
      this.notebookItem = Object.assign({}, item);
      this.dialog = true;
    },

    deleteItem(item) {
      confirm("Are you sure you want to delete this item?") &&
        this.$http
          .del(this.$url.deleteNotebookInfo, item.notebookID)
          .then((res) => {
            if (res > 0) {
              this.initialize();
            }
          });
    },

    close() {
      this.dialog = false;
      this.$nextTick(() => {
        this.notebookItem = Object.assign({}, this.defaultItem);
        this.notebookItem.expenseDate = this.ExpenseDate;
        this.isEdit = false;
      });
      this.resetValidation();
    },

    save() {
      this.validate();
      if (this.$refs.form.value) {
        if (this.isEdit) {
          this.$http
            .post(this.$url.updateNotebookInfo, this.notebookItem)
            .then((res) => {
              if (res && res.responseType === "success") {
                this.initialize();
              }
            });
        } else {
          this.$http
            .post(this.$url.insertNotebookInfo, this.notebookItem)
            .then((res) => {
              if (res > 0) {
                this.initialize();
              }
            });
        }
        this.close();
      }
    },
    getMonthExpense() {
      this.$http
        .get(this.$url.getMonthExpense, {
          strYearMonth: this.strYearMonth,
        })
        .then((res) => {
          this.monthExpense = res === null ? 0 : res;
        });
    },
    getDayExpense() {
      this.$http
        .get(this.$url.getDayExpense, {
          strDay: this.ExpenseDate,
        })
        .then((res) => {
          this.dayExpense = res === null ? 0 : res;
        });
    },
  },
};
</script>