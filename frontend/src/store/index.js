import { createStore } from 'vuex'
import VuexPersistence from 'vuex-persist'

export default createStore({
  state: {
    articleId:0,
    latestcGroup:0,
    token:0,
    email:0,
    userid:0,
    authorid:0
  },
  getters: {
  },
  mutations: {
    setArticleId(state, num){
      state.articleId = num
    },
    setcLatestcGroup(state, group){
      state.latestcGroup = group
    },
    setToken(state, payload){
      state.token = payload
    },
    setEmail(state, payload){
      state.email = payload
    },
    setUserId(state, payload){
      state.userid = payload
    },
    setAuthorid(state, payload){
      state.authorid = payload
    }
  },
  actions: {
  },
  modules: {
  },
  plugins: [new VuexPersistence().plugin]
},

)
