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
    sendMail: 'mail/send',
    //file
    getFilesAfterUpload: "file/getFilesAfterUpload",
    downloadFile: "file/downloadFile",
    getFiles: "file/getFiles",
    deleteFiles: "file/deleteFiles",
    preview: "file/preview",
}
Object.keys(api).forEach(el => {
    api[el] = "api/" + api[el]
})

export default api