const tabs = {
    state: {
        // 通过name去激活是否当前tab
        activeLabel: "",
        // 每个tab对应的route
        activeRoute: "",
        // 存放所有的tab
        tabList: []
    },

    mutations: {
        SET_LABEL(state, activeLabel) {
            state.activeLabel = activeLabel
        },
        SET_ROUTE(state, activeRoute) {
            state.activeRoute = activeRoute
        },
        SET_TAB_LIST(state, tab) {
            state.tabList.push(tab)
        },
        // 删除tabs
        DELETE_TABS(state, targetRoute) {
            let index = -1;
            let tabs = state.tabList
            for (let tab of tabs) {
                index++;
                // 是否可关闭
                if (tab.closeable) {
                    if (targetRoute === tab.route) {
                        // 如果将要关闭的tab路由是当前激活路由
                        if (state.activeRoute === targetRoute) {
                            // 激活前一个tab
                            state.activeLabel = state.tabList[index - 1].label;
                            state.activeRoute = state.tabList[index - 1].route;
                            state.tabList.splice(index, 1);
                            break;
                        }
                        else {
                            state.tabList.splice(index, 1);
                        }
                    }
                }
            }
        },
    },

    actions: {
        addTab({ commit }, tabInfo) {
            commit('SET_TAB_LIST', tabInfo)
            commit('SET_LABEL', tabInfo.label)
            commit('SET_ROUTE', tabInfo.route)
        },
        removeTab({ commit }, targetRoute) {
            commit('DELETE_TABS', targetRoute)
        }
    },
}

export default tabs