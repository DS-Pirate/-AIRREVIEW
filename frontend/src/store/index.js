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
    }
  },
  actions: {
  },
  modules: {
  }
})
