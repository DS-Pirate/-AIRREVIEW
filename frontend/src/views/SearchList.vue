<template>
  <div class="searchlist">
  <div class="all">
    <!-- 검색필터 -->
    <div class="btn-group" role="group" aria-label="Default button group">
      <button class="btn btn-outline-dark" @click="view()">조회순</button>
      <button type="button" class="btn btn-outline-dark">좋아요순</button>
      <button type="button" class="btn btn-outline-dark">평점순</button>
      <button type="button" class="btn btn-outline-dark" @click="latest()">최신순</button>
    </div>

    <!-- 검색카드 -->
    <div class="row row-cols-1">
      <div v-for="(card, idx) in state.cards" :key="idx">
        <Cards :card="card"></Cards>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
// @ is an alias to /src

import Cards from "@/components/MainCards.vue";
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
    })
    const router = useRouter()
    let searchword = new URLSearchParams(window.location.search).get("cards");


    //getCards
    async function getCardsInformation(){
      let orderword = new URLSearchParams(window.location.search).get("order");
      console.log("1. order : " + orderword);
      const url= `/airreview/article/search`
      const headers = {
        "Content-Type": "application/json; charset=utf-8",
      }
      const body = {
        search: searchword,
        order: orderword
      }
      console.log("2." + body);
      await axios.post(url, body, {headers}).then(function (res){
        console.log("3. 시작");
        console.log(res.data);
        state.cards = 0;
        state.cards = res.data;
        console.log("4. 끝")
      })
    }
    async function view (){
      await router.push(`/search?cards=${searchword}&order=view`);
      await router.go(0)
    }

    async function latest(){
      await router.push(`/search?cards=${searchword}&order=new`);
      await router.go(0)
    }

      getCardsInformation()

  return {state, view, latest }
  },
};
</script>

<style></style>