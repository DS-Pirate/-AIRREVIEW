import { createStore } from 'vuex'

export default createStore({
  state: {
    articleId:0
  },
  getters: {
  },
  mutations: {
    setArticleId(state, num){
      state.articleId = num
      sessionStorage.setItem("aid", num) 
    },

  },
  actions: {
  },
  modules: {
  }
})
