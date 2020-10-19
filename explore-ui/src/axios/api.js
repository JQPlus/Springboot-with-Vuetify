const api = {
    // login module
    login: "login/login",
    logout: "login/logout",
    verify: "login/verify",
    // notebook module
    retrieveDailyExpense: 'notebook/retrieveDailyExpense',
    updateNotebookInfo: 'notebook/update',
    insertNotebookInfo: 'notebook/insert',
    deleteNotebookInfo: 'notebook/delete',
    retrieveByExpenseDate: 'notebook/retrieveByExpenseDate',
    retrieveByExpenseDateRange: 'notebook/retrieveByExpenseDateRange',
    getMonthExpense: 'notebook/getMonthExpense',
    getDayExpense: 'notebook/getDayExpense',
    // category module
    getCategoryByTypeCode: 'category/getCategoryByTypeCode',
    //mail
    sendMail:'mail/send'
}

export default api