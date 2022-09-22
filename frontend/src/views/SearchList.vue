<template>
  <div class="searchlist">
  <div class="all">
    <!-- 검색필터 -->
    <div class="btn-group" role="group" aria-label="Default button group">
      <button type="button" class="btn btn-outline-dark">조회순</button>
      <button type="button" class="btn btn-outline-dark">좋아요순</button>
      <button type="button" class="btn btn-outline-dark">평점순</button>
      <button type="button" class="btn btn-outline-dark">최신순</button>
    </div>

    <!-- 검색카드 -->
    <div class="row row-cols-1">
      <div v-for="(card, idx) in state.cards" :key="idx">
        <Cards :card="card"></Cards>
      </div>
<!--      <p v-for="(card, idx) in state.cards" :key="idx">{{card}}</p>-->
    </div>
  </div>
  </div>
<!--  {{searchWord}}-->
</template>

<script>
// @ is an alias to /src

import Cards from "@/components/MainCards.vue";
import {reactive} from "@vue/reactivity";

// import { useRouter } from 'vue-router'
import axios from "axios";
import { useStore } from "vuex";
import {computed} from "vue";

export default {
  name: "SearchList",
  components: {
    Cards,
  },
  setup() {
    const store = useStore();
    const state = reactive({
      cards: 0,
    })

    const searchWord = computed(()=>{
      return store.state.searchWord
    })


    let id = new URLSearchParams(window.location.search).get("cards");
    console.log(id);
    console.log("이동완료(searchList)")

    function getCardsInformation(){
      const url= `/airreview/article/search`
      const headers = {
        "Content-Type": "application/json; charset=utf-8",
      }
      const body = {
        search: id
      }
      axios.post(url, body, {headers}).then(function (res){
        console.log("시작");
        console.log(res.data);
        state.cards = res.data;
        console.log("끝")
        // router.push(`/search?cards=${store.state.searchWord}`)
      })
    }

      getCardsInformation()

  return {state, searchWord}
  },
};
</script>

<style></style>