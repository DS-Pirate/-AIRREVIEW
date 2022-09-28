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
    // reqPage: state.reqPage
  }
  // if(body.reqPage == 0)
    state.cards = null;
  console.log(body);
  await axios.post(url, body, {headers}).then(function (res) {
    console.log("3. 시작");
    console.log(res.data);
    state.cards = res.data;
    console.log("4. 끝")
  })
}
getSub()

</script>

<style scoped lang="scss">
</style>
