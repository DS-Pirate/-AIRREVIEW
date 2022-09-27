<template>
<div >
  <div class="image-contener">
    <div class="position-relative">
    <div div class=" ms-2 mt-2 badge bg-secondary position:absolute"  v-if="state.following == store.state.userid && $store.state.token">
      구독중
    </div>
    <img  src="../assets/pngwing.com.png" alt="">
    </div>
  </div>
  <div class="meta-contener">
    <h2 class="title">{{card.airName}}</h2>
    <span class="desc">{{card.myuserid}}가 {{card.userid}}를 구독하고 있습니다. 여기 자기소개칸!</span>
  </div>
  <div class="detail mb-1">
    <div class="row align-items-start">
      <div class="col">
        <span class="detailWriting">Posts</span>
        <br>
        <span class="detailNumbers">{{card.post}}</span>
      </div>
      <div class="col">
        <span class="detailWriting">Followers</span>
        <br>
        <span class="detailNumbers">{{card.follower}}</span>
      </div>
      <div class="col">
        <span class="detailWriting">Following</span>
        <br>
        <span class="detailNumbers">{{card.following}}</span>
      </div>
    </div>
  </div>
  <div class="button-contener">
    <button class="button unfollow" v-if="store.state.userid == state.following && $store.state.token" @click="followClick()">Unfollow</button>
    <button class="button followBtn" v-if="!(store.state.userid == state.following ) && $store.state.token" @click="followClick()">Follow</button>
    <a href="/login" class="button unfollow" v-if="!$store.state.token">Login</a>
    <button class="button readMore" @click="readMore(card.userid)">Read More</button>
  </div>
</div>
</template>

<script setup>
import { defineProps, reactive } from "vue";
import router from "@/router";
import axios from "axios";
import store from "@/store";

const props = defineProps({
  card: Object
});
const state = reactive({
  following: ''
})
console.log(props.card);

//구독유무
function getSub(){
  const url = store.state.axiosLinkAdmin+`/api/followyn`;
  const headers = {
    "Content-Type": "application/json; charset=utf-8",
    "Authorization": store.state.token,
    "userid": store.state.userid,
  };
  const body = {
    userid: store.state.userid,
    aid: props.card.userid
  }
  console.log(body);
  state.following =  '';
  axios.post(url, body, {headers}).then(function (res) {
    console.log("구독유무");
    console.log(res.data);
    state.following = res.data;
  })
}

//구독 버튼
async function followClick(){
  const url = store.state.axiosLinkAdmin+`/api/follow`;
  const headers = {
    "Content-Type": "application/json; charset=utf-8",
    "Authorization": store.state.token,
    "userid": store.state.userid,
  };
  const body = {
    userid: store.state.userid,
    aid: props.card.userid
  }
  await axios.post(url, body, {headers}).then(function (res) {
    console.log("구독취소유무");
    console.log(res.data);
    if(res.data == "구독취소"){
      alert("구독을 취소하였습니다!")
    } else {
      alert( props.card.airName + `님을 구독하였습니다!`)
    }
  })
  await getSub()
}

getSub()

async function readMore(a) {
  await router.push(`/mypage?channel=${a}`);
  router.go(0)
}
</script>

<style scoped>

</style>