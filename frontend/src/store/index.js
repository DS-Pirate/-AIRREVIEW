import { createStore } from 'vuex'

export default createStore({
  state: {
    articleId:0,
    latestcGroup:0
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
    }

  },
  actions: {
  },
  modules: {
  }
})
