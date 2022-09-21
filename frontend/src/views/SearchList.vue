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
      <p v-for="(card, idx) in state.cards" :key="idx">{{card}}</p>
    </div>
  </div>
  </div>
</template>

<script>
// @ is an alias to /src

// import Cards from "@/components/MainCards.vue";

// import axios from "axios";
import {reactive} from "@vue/reactivity";

export default {
  name: "SearchList",
  components: {
    // Cards,
  },
  setup() {
    const state = reactive({
      cards: []
    })
  //검색처리 하는중!

    // getCardsInformation();
  return {state,}
  },
};
</script>

<style></style>