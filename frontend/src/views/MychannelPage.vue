<template>
    <div class="channelpage">
        <div class="chimg p-3 m-0 shadow-sm bg-white">
            <img :src="'https://hyns.co.kr/airreview/images/read/ch/' + id" class="ch_img w-100" alt="" />
            <input type="file" v-on:change="fileUpload($event)" class="w-100 h-100 fileinput form-control" id="formFile" v-if="store.state.userid==id"/>
        </div>
        <div class="chcategory p-1 my-3 shadow-sm bg-white d-flex justify-content-center">
            <ul class="list-group list-group-horizontal h-10 align-items-center">
                <li class="list-group-item border border-0" v-on:click="change('list')">글 목록</li>
                <span>|</span>
                <li class="list-group-item border border-0" v-on:click="change('category')">Following</li>
              <div div class="badge bg-secondary position:absolute" v-if="state.followingCount != 0"> {{ state.followingCount }} </div>
                <span>|</span>
              <li class="list-group-item border border-0" v-on:click="change('follower')">Follower</li>
              <div div class="badge bg-secondary position:absolute" v-if="state.followerCount != 0" > {{ state.followerCount }} </div>
              <span>|</span>
                <li class="list-group-item border border-0" v-on:click="change('channelinfo')">내 정보</li>
                <span>|</span>
              <button type="button"  class="list-group-item border border-0 followsubmit" v-if="!(store.state.userid == state.following ) && $store.state.token" @click="followsubmit()" >구독하기</button>
              <button type="button"  class="list-group-item border border-0 followsubmit" v-if="store.state.userid == state.following && $store.state.token" @click="followsubmit()" >구독해지</button>
            </ul>
          <form action="" class="searching-area d-flex align-items-center gap-1" @submit.prevent="searchingAxios()">
            <input type="text" v-model="state.context" class="form-control border-0 bg-white " @submit="searchingAxios()">
            <label for="searching"><i class="bi bi-search"></i></label>
          </form>




        </div>
        <div class="chcategory p-3 my-3 shadow-sm bg-white d-flex h-auto" v-if="state.form == 'list'">
            <PostList :id="id" />
        </div>
        <div class="chcategory p-3 my-3 shadow-sm bg-white d-flex h-auto" v-else-if="state.form == 'category'">
            <SubInfo :id="id" />
        </div>
      <div class="chcategory p-3 my-3 shadow-sm bg-white d-flex h-auto" v-else-if="state.form == 'follower'">
        <FollowerInfo :id="id" />
      </div>
        <div class="chcategory p-3 my-3 shadow-sm bg-white d-flex h-auto" v-else-if="state.form == 'channelinfo'">
            <MyInfo />
        </div>
    </div>
</template>
<script setup>
import {onMounted, reactive} from "vue";
    import SubInfo from "@/components/SubscriptionInfo.vue";
    import PostList from "@/components/PostList.vue";
    import MyInfo from "@/components/MyInfo.vue";
    import axios from "axios";
    import store from "@/store";
    import FollowerInfo from "@/components/FollowerInfo";
import {useRouter} from "vue-router/dist/vue-router";

const router = useRouter()

    let state = reactive({
        form: "list",
      following: '',
      followingCount:0,
      followerCount:0,
      context:''
    });

    let id = new URLSearchParams(window.location.search).get("channel");

  function searchingAxios(){
    if (state.context.trim().length == 0){
      return
    }

    async function routing (){
      await router.push(`/mypage?channel=${id}&search=${state.context}`)
      await router.go(0)
    }
    routing();
  }

    function change(which) {
        state.form = which;
      async function routinglist (){
        await router.push(`/mypage?channel=${id}`)
        await router.go(0)
      }
        if(which == "list"){
          routinglist ()
        }
    }

    let headers = { token: store.state.token };

    function fileUpload(e) {
        let formData = new FormData();
        formData.append("upload", e.target.files[0]);
        axios.post(`${store.state.axiosLink}/mypage/image/upload/${store.state.userid}`, formData, { headers }).then(function () {
            router.go(0)
        });
    }


    async function followsubmit(){
      const url = store.state.axiosLink+`/api/follow`;
      const headers = {
        "Content-Type": "application/json; charset=utf-8",
        "Authorization": store.state.token,
        "userid": store.state.userid,
      };
      const body = {
        userid: store.state.userid,
        aid: id
      }
      await axios.post(url, body, {headers}).then(function (res) {
        if(res.data == "구독취소"){
          router.go(0);
          alert("구독을 취소하였습니다!")
        } else {
          router.go(0);
          alert(`구독하였습니다!`)
        }
      })
      await getSub()
      await getCount();
    }

    function getSub(){
      const url = store.state.axiosLink+`/api/followyn`;
      const headers = {
        "Content-Type": "application/json; charset=utf-8",
        "Authorization": store.state.token,
        "userid": store.state.userid,
      };
      const body = {
        userid: store.state.userid,
        aid: id
      }
      state.following =  '';
      axios.post(url, body, {headers}).then(function (res) {
        state.following = res.data;
      })
    }

    function getCount(){
      const url = store.state.axiosLink+`/mypage/followcount`;
      const headers = {
        "Content-Type": "application/json; charset=utf-8"
      };
      const body = {
        userid: id
      }
      axios.post(url, body, {headers}).then(function (res) {
        state.followingCount = res.data.following;
        state.followerCount = res.data.follower;
      })
    }

    onMounted(() => {
      getSub()
      getCount()
    })

</script>
<style lang="sass" scoped>
    .chimg
        position: relative
    .fileinput
        position: absolute
        opacity: 0
        top:0
        left: 0
        transition: 0.2s all ease-in-out
        &:hover
            opacity: 1
</style>
