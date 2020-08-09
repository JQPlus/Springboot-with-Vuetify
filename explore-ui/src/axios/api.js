const api = {
    // login module
    login: "login/login",
    logout: "login/logout",
    verify: "login/verify",
    // notebook module
    retrieveAllNotebook: 'notebook/retrieveAllNotebook',
    updateNotebookInfo: 'notebook/update',
    insertNotebookInfo: 'notebook/insert',
    deleteNotebookInfo: 'notebook/delete',
    retrieveByExpenseDate: 'notebook/retrieveByExpenseDate',
    retrieveByExpenseDateRange: 'notebook/retrieveByExpenseDateRange',
    // category module
    getCategoryByTypeCode: 'category/getCategoryByTypeCode',
}

export default api