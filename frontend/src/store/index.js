import { createStore } from 'vuex'

export default createStore({
  state: {
    articleId:0,
    latestcGroup:0,
    token:0,
    email:0,
  },
  getters: {
  },
  mutations: {
    setArticleId(state, num){
      state.articleId = num
      sessionStorage.setItem("aid", num)
    },
    setcLatestcGroup(state, group){
      state.latestcGroup = group
    },
    setToken(state, payload){
      state.token = payload
      sessionStorage.setItem("email", payload)
    },
    setEmail(state, payload){
      state.email = payload

      sessionStorage.setItem("TOKEN", payload)
    },
  },
  actions: {
  },
  modules: {
  },
})
