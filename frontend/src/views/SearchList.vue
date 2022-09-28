<template>
  <div class="searchlist">
<!--  <div class="all">-->
    <!-- 검색필터 -->
    <div class="btn-group mb-4 ms-4 ps-1" role="group" aria-label="Default button group">
      <button class="btn btn-outline-dark" @click="view()">조회순</button>
      <button type="button" class="btn btn-outline-dark" @click="like()">좋아요순</button>
      <button type="button" class="btn btn-outline-dark" @click="star()">평점순</button>
      <button type="button" class="btn btn-outline-dark" @click="latest()">최신순</button>
    </div>

    <!-- 검색카드 -->
    <div class="mb-4 mt-5 ms-4 ps-1 text-center" v-if="state.notSearchWord">
      <h1>검색 결과가 없습니다</h1>
    </div>
    <div class="row row-cols-3">
      <div v-for="(card, idx) in state.cards" :key="idx">
        <Cards :card="card"></Cards>
      </div>
    </div>
  </div>
<!--  </div>-->
</template>

<script>
// @ is an alias to /src

import Cards from "@/components/MainCards.vue";
import store from "@/store";
import {reactive} from "@vue/reactivity";
import axios from "axios";
import {useRouter} from "vue-router/dist/vue-router";

export default {
  name: "SearchList",
  components: {
    Cards,
  },
  setup() {
    const state = reactive({
      cards: 0,
      notSearchWord : false,
      reqPage: 0,
      pageTotalCount: 0,
    })
    const router = useRouter()
    let searchword = new URLSearchParams(window.location.search).get("cards");


    //getCards
    async function getCardsInformation(){
      let orderword = new URLSearchParams(window.location.search).get("order");
      console.log("1. order : " + orderword);
      const url= `${store.state.axiosLink}/article/search`
      const headers = {
        "Content-Type": "application/json; charset=utf-8",
      }
      const body = {
        search: searchword,
        order: orderword,
        reqPage: state.reqPage
      }
      console.log(body)
      if (state.reqPage == 0) state.cards = null;
      await axios.post(url, body, {headers}).then(function (res){
        state.pageTotalCount = res.data.pageTotalCount;
        if (body.reqPage == 0) {
          state.cards = res.data.articles;
        } else {
          for (let i = 0; i < 9; i++) {
            state.cards.push(res.data.articles[i]);
          }
        }
      })
    }

    async function view (){
      state.reqPage=0
      await router.push(`${store.state.axiosLink}/search?cards=${searchword}&order=view`);
      getCardsInformation()
    }

    async function latest(){
      state.reqPage=0
      await router.push(`${store.state.axiosLink}/search?cards=${searchword}&order=new`);
      getCardsInformation()
    }

    async function star(){
      state.reqPage=0
      await router.push(`${store.state.axiosLink}/search?cards=${searchword}&order=star`);
      getCardsInformation()
    }

    async function like(){
      state.reqPage=0
      await router.push(`${store.state.axiosLink}/search?cards=${searchword}&order=like`);
      getCardsInformation()
    }

    function getMoreCards(){
      state.reqPage+=1
      getCardsInformation()
    }

    window.addEventListener("scroll", windowSize)
    function windowSize(){
      if(window.innerHeight+window.scrollY>=document.body.offsetHeight){
        getMoreCards()
      }
    }

      getCardsInformation()

  return {state, view, latest, star, like }
  },
};
</script>

<style></style>