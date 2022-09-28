<template>
    <div class="subInfo">
          <div class="index-post">
          <Cards v-for="(card, idx) in state.cards" :key="idx" class="contener" :card="card" />
        </div>
    </div>
</template>

<script setup>
import Cards from "@/components/SubsCards.vue";
import { reactive, defineProps } from "vue";
import axios from "axios";
import store from "@/store";

let props = defineProps(["id"])
const state = reactive({
  cards : [],
  reqPage: 0,
    })

async function getSub(){
  const url = store.state.axiosLink+`/mypage/following`
  const headers = {
    "Content-Type": "application/json; charset=utf-8",
  }
  const body = {
    userid: props.id,
    aid: 0,
    reqPage: state.reqPage
  }
  if(body.reqPage == 0) state.cards = null;
  console.log("body :" + body);
  await axios.post(url, body, {headers}).then(function (res) {
    if (body.reqPage == 0) {
      state.cards = res.data;
    } else {
      for (let i = 0; i < 9; i++) {
        state.cards.push(res.data[i]);
      }
    }
  })
}

function getMoreCards(){
  state.reqPage+=1
  getSub()
}

window.addEventListener("scroll", windowSize)
function windowSize(){
  if(window.innerHeight+window.scrollY>=document.body.offsetHeight){
    getMoreCards()
  }
}


getSub()

</script>

<style scoped lang="scss">
</style>
