const getters = {
    token: state => state.login.token,
    roles: state => state.login.roles,
    EID: state => state.login.EID,
    userName: state => state.login.userName,
}
export default getters
