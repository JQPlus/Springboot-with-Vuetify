import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'

Vue.use(Vuex)


const modulesFiles = require.context('./modules', true, /\.js$/)
// you do not need `import app from './modules/app'`
// it will auto require all vuex module from modules file
const modules = modulesFiles.keys().reduce((modules, modulePath) => {
    // get module name './app.js' => 'app'
    const moduleName = modulePath.replace(/^\.\/(.*)\.\w+$/, '$1')
    // 得到对应路径下module
    const value = modulesFiles(modulePath)
    modules[moduleName] = value.default
    return modules
}, {})

const store = new Vuex.Store({
    modules,
    getters
})

export default store