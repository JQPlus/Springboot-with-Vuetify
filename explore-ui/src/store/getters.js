const getters = {
    token: state => state.login.token,
    roles: state => state.login.roles,
    account: state => state.login.account,
    userName: state => state.login.userName,
}
export default getters
