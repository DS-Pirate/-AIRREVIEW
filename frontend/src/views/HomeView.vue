<template>
  <div class="mainviewpage">

    <div class="btn-group mb-4 ms-4 ps-2" role="group" aria-label="Default button group">
      <button class="btn btn-outline-dark" @click="view()">조회순</button>
      <button type="button" class="btn btn-outline-dark" @click="like()">좋아요순</button>
      <button type="button" class="btn btn-outline-dark" @click="star()">평점순</button>
      <button type="button" class="btn btn-outline-dark" @click="latest()">최신순</button>
      <button type="button" class="btn btn-outline-dark" @click="sub()">구독한 글</button>
    </div>

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
    function getCardsInformation() {
      const url = "/airreview/article/card"
      axios.get(url).then((res) => {
        console.log("3. 시작");
        console.log(res.data);
        state.cards = 0;
        state.cards = res.data;
        console.log("4. 끝")
      })
    }

      async function order(a) {
        const url = `/airreview/article/card/order`
        const headers = {
          "Content-Type": "application/json; charset=utf-8",
        }
        const body = {
          search: "null",
          order: a
        }
        state.cards = null;
        console.log(body);
        await axios.post(url, body, {headers}).then(function (res) {
          console.log("3. 시작");
          console.log(res.data);
          state.cards = res.data;
          console.log("4. 끝")
        })
      }


    async function view (){
      order("view")
    }
    async function like (){
      order("like")
    }
    async function star (){
      order("star")
    }
    async function latest (){
      order("latest")
    }

    getCardsInformation()


    return {state, view, like, star, latest}
  }
}

</script>
