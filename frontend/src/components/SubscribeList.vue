<template>
    <ul class="flex-column p-0 subscribelist" v-if="state.show"><br>
      <li class="nav-item d-flex m-0 p-0">
        <button class="me-2 border border-0 bg-white" @click="unfollowsubmit()">x</button>
          <a :href="'/mypage?channel=' + props.subsinfo.userid" class="nav-link d-flex justify-content-center align-items-left" aria-current="page">
            <img  :src="store.state.axiosLink+'/images/read/'+props.subsinfo.fileName" width="25" height="25"  style="border-radius: 50%;" class="me-1" alt="">
            <span>{{props.subsinfo.airName}}</span>
          </a>
      </li>
  </ul>
</template>
<script setup>
import {defineProps, reactive} from 'vue';
  import store from "@/store";
  import axios from "axios";
  let props = defineProps(["subsinfo"])

  let state = reactive({
    show:true,
  })

  async function unfollowsubmit(){
    const url = store.state.axiosLink+`/api/follow`;
    const headers = {
      "Content-Type": "application/json; charset=utf-8",
      "Authorization": store.state.token,
      "userid": store.state.userid,
    };
    const body = {
      userid: store.state.userid,
      aid: props.subsinfo.userid
    }
    await axios.post(url, body, {headers}).then(function (res) {
      if(res.data == "구독취소"){
        alert("구독을 취소하였습니다!")
        state.show=false;

      } else {
        alert(`구독하였습니다!`)
      }
    })
  }
</script>
<style lang="">
    
</style>