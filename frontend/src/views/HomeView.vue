<template>
  <div class="mainviewpage">
    <div class="fav p-1">
      <div  class="row row-cols-3">
        <Cards :cardinfo="i" v-for="i in 25" :key="i"></Cards>
      </div>
    </div>
  </div>

<!--        더미!! 나중에 삭제!!!!-->
<!--    <p v-for="(card, idx) in state.cards" :key="idx">{{card}}</p>-->
</template>

<script setup>
import Cards from "@/components/MainCards.vue";

</script>

<!--<script>-->
<!--import Cards from "@/components/MainCards.vue";-->
<!--import axios from "axios";-->
<!--import {reactive} from "@vue/reactivity";-->
<!--// sessionStorage.clear()-->

<!--export default {-->
<!--  name: "HomeView",-->
<!--  components: {Cards},-->
<!--  setup() {-->
<!--    const state = reactive({-->
<!--      cards: []-->
<!--    })-->

<!--    const url = "/airreview/article/card"-->
<!--    axios.get(url).then(({data}) => {-->
<!--      console.log(data);-->
<!--      state.cards = data;-->
<!--    })-->
<!--    return {state}-->
<!--  }-->
<!--}-->

<!--</script>-->
