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

      //()순 정렬
      async function order(order, view,like,star,latest) {
        state.main = false;
        state.sub = false;
        state.view = view;
        state.like =like;
        state.star = star;
        state.latest = latest;
        const url = `${store.state.axiosLink}/article/card/order`
        const headers = {
          "Content-Type": "application/json; charset=utf-8",
        }
        const bodyOrder = {
          search: "null",
          order: order,
          reqPage: body.reqPage
        }
        if(body.reqPage == 0) state.cards = null;
        console.log(bodyOrder);
        await axios.post(url, bodyOrder, {headers}).then(function (res) {
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
      body.reqPage=0
      order("view", true,false,false,false);
    }
    async function like (){
      body.reqPage=0
      order("like", false,true, false,false)
    }
    async function star (){
      body.reqPage=0
      order("star", false,false, true,false)
    }
    async function latest (){
      body.reqPage=0
      order("latest", false,false,false, true)
    }

    async function sub(){
      state.sub = true;
      state.view = false;
      state.like =false;
      state.latest = false;
      state.star = false;
      state.main = false;
      const url = `${store.state.axiosLink}/api/article/card/sub`
      const headers = {
        "Content-Type": "application/json; charset=utf-8",
        "Authorization": store.state.token,
        "userid" : store.state.userid
      }
      const subbody = {
        userid : store.state.userid,
        reqPage : body.reqPage
      }
      if(body.reqPage == 0) state.cards = null;
      console.log(subbody);
      await axios.post(url, subbody, {headers}).then(function (res) {
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

    function getMoreCards(){
      body.reqPage+=1
      if (state.main == true) {
        getCardsInformation()
      } else if (state.sub == true){
        sub()
      }else if (state.view == true){
        order("view",true,false,false,false);
      }else if (state.like == true){
        order("like", false,true, false,false);
      }else if (state.star == true){
        order("star", false,false,true, false);
      }else if (state.latest == true){
        order("latest", false,false,false, true);
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
