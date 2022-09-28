<template>
  <div class="mainviewpage">

    <div class="btn-group mb-4 ms-4 ps-2" role="group" aria-label="Default button group">
      <button class="btn btn-outline-dark" @click="view()">조회순</button>
      <button type="button" class="btn btn-outline-dark" @click="like()">좋아요순</button>
      <button type="button" class="btn btn-outline-dark" @click="star()">평점순</button>
      <button type="button" class="btn btn-outline-dark" @click="latest()">최신순</button>
      <button type="button" class="btn btn-outline-dark" @click="sub()" v-if="$store.state.token">구독한 글</button>
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
import store from "@/store";

export default {
  name: "HomeView",
  components: {Cards},
  setup() {
    const state = reactive({
      cards: [],
      pageTotalCount: 0,
      main : true,
      view : false,
      like : false,
      star : false,
      latest : false,
      sub : false,
    })
    let body = reactive({
      reqPage: 0,
    });
    //state.cards 날리기
    function getCardsInformation() {
      console.log("body"+body.reqPage);
        const url = store.state.axiosLink + "/article/card"
        axios.post(url, body).then((res) => {
          console.log(res.data);
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


      async function order(order) {
        state.main = false;
        state.sub = false;

        const url = `${store.state.axiosLink}/article/card/order`
        const headers = {
          "Content-Type": "application/json; charset=utf-8",
        }
        const body = {
          search: "null",
          order: order
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
      body.reqPage=0
      state.like =false;
      state.latest = false;
      state.star = false;
      order("view");
    }
    async function like (){
      body.reqPage=0
      order("like")
    }
    async function star (){
      body.reqPage=0
      order("star")
    }
    async function latest (){
      body.reqPage=0
      order("latest")
    }

    async function sub(){
      const url = `${store.state.axiosLink}/api/article/card/sub`
      const headers = {
        "Content-Type": "application/json; charset=utf-8",
        "Authorization": store.state.token,
        "userid" : store.state.userid
      }
      const body = {
        userid : store.state.userid,
        aid : 0
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

    function getMoreCards(){
      body.reqPage+=1
      if (state.main == true) {
        getCardsInformation()
      }
    }

    window.addEventListener("scroll", windowSize)
    function windowSize(){
      if(window.innerHeight+window.scrollY>=document.body.offsetHeight){
        getMoreCards()
      }
      }

      getCardsInformation()
    return {state, view, like, star, latest, sub}
  }
}

</script>
