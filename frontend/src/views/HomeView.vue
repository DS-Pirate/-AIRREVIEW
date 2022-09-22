<template>
  <div class="mainviewpage">
    <div class="fav p-1">
      <div  class="row row-cols-3">
        <div v-for="(card, idx) in state.cards" :key="idx">
          <Cards :card="card"></Cards>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Cards from "@/components/MainCards.vue";
import axios from "axios";
import {reactive} from "@vue/reactivity";

export default {
  name: "HomeView",
  components: {Cards},
  setup() {
    const state = reactive({
      cards: [],
    })



    const url = "/airreview/article/card"
    axios.get(url).then((res) => {
      state.cards = res.data;
    })
    return {state}
  }
}

</script>
