<template>
  <div class="mainviewpage">
    <div class="fav p-1">
      <div  class="row row-cols-3">
<!--        <Cards :cardinfo="i" v-for="i in 25" :key="i"></Cards>-->
        <div v-for="(card, idx) in state.cards" :key="idx">
          <Cards :card="card"></Cards>
        </div>
      </div>
    </div>
<!--            나중에 삭제 예정!!!!-->
        <p v-for="(card, idx) in state.cards" :key="idx">{{card[1]}}</p>
  </div>
<!--3 : 글-->
</template>

<!--<script setup>-->
<!--import Cards from "@/components/MainCards.vue";-->

<!--</script>-->

<script>
import Cards from "@/components/MainCards.vue";
import axios from "axios";
import {reactive} from "@vue/reactivity";

export default {
  name: "HomeView",
  components: {Cards},
  setup() {
    const state = reactive({
      cards: []
    })



    const url = "/airreview/article/card"
    axios.get(url).then((res) => {
      state.cards = res.data;
      const year = Date.parse(state.cards[1][4]);
      console.log(typeof state.cards[1][4])
      console.log(new Date(year).getFullYear());
    })
    return {state}
  }
}

</script>
