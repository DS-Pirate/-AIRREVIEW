<template>
<div >
  <div class="image-contener">
    <div div class=" ms-2 mt-2 badge bg-secondary position-absolute" :style="`z-index: 1;`" v-if="state.following == store.state.userid && $store.state.token">
      구독중
    </div>
    <div class="position-relative">

<!--    <img  src="../assets/pngwing.com.png" alt="">-->
      <img  :src="store.state.axiosLink+'/images/read/'+image" alt="">
    </div>
  </div>
  <div class="meta-contener">
    <h2 class="title">{{card.airName}}</h2>
    <span class="desc">{{intro}}</span>
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
let intro = introNull(props.card.userIntro);

let image = imageslice(props.card.fileName, props.card.idx);
function imageslice(a, b){
  if(a == null || b == 99){
    return "basic.png"
  } else { return a}
}

function introNull(intro){
  if(intro != null){
    return intro
  } return props.card.airName + "님은 아직 등록된 자기소개가 없습니다."
}

console.log(props.card);

//구독유무
function getSub(){
  const url = store.state.axiosLink+`/api/followyn`;
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
  const url = store.state.axiosLink+`/api/follow`;
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
console.log(props.card)
</script>

<style scoped>

</style>