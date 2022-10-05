<template>
  <div class="searchlist">
<!--  <div class="all">-->
    <!-- 검색필터 -->
    <div class="mb-4 ms-4 ps-2 d-flex flex-row-reverse" role="group" aria-label="Default button group">
      <button  class="button-81" :class="{btnblue: state.view === true }" @click="view()">조회순</button>
      <button class="button-81" :class="{btnblue: state.like === true }" type="button" @click="like()">좋아요순</button>
      <button class="button-81" :class="{btnblue: state.star === true }" type="button"  @click="star()">평점순</button>
      <button class="button-81" :class="{btnblue: state.latest === true }" type="button" @click="latest()">최신순</button>
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
      view : false,
      like : false,
      star : false,
      latest : false,
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
      if (state.cards.length == 0){
        state.notSearchWord = true;
      } else state.notSearchWord = false;
    }
    function btntrue(view,like,star,latest) {
      state.view = view;
      state.like =like;
      state.star = star;
      state.latest = latest;}

    async function view (){
      state.reqPage=0
      await btntrue(true, false, false, false)
      await router.push(`${store.state.axiosLink}/search?cards=${searchword}&order=view`);
      getCardsInformation()
    }

    async function latest(){
      state.reqPage=0
      await btntrue( false, false,false, true)
      await router.push(`${store.state.axiosLink}/search?cards=${searchword}&order=new`);
      getCardsInformation()
    }

    async function star(){
      state.reqPage=0
      await btntrue( false, false, true,false )
      await router.push(`${store.state.axiosLink}/search?cards=${searchword}&order=star`);
      getCardsInformation()
    }

    async function like(){
      state.reqPage=0
      await btntrue( false, true,false, false)
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

+<style lang="sass">

@media (min-width: 768px)
  .button-81
    font-size: 1.125rem
    padding: 1rem 2rem


.button-81
  font-family: Pretendard-Regular
  background-color: #fff
  border: 0 solid #e2e8f0
  border-radius: 1.5rem
  box-sizing: border-box
  color: #0d172a
  cursor: pointer
  display: inline-block
  font-size: 0.9rem
  font-weight: 400
  line-height: 1
  padding: 0.5rem 1.1rem
  text-align: center
  text-decoration: none #0d172a solid
  text-decoration-thickness: auto
  transition: all .1s cubic-bezier(.4, 0, .2, 1)
  user-select: none
  -webkit-user-select: none
  touch-action: manipulation

//.button-81:hover, .button-81:active
//  background-color: #0D6EFD
//  color: #fff

.btnblue
  background-color: #0D6EFD
  color: #fff

</style>