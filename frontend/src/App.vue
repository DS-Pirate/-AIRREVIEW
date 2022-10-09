<template>
  <metainfo />
  <div class="content-area container-fluid d-flex justify-content-between p-4 gap-3">
    <SubNavigationBar class="w-15 px-4"/>
    <div class="routing-section w-85">
      <div class="routing-header mb-5 mt-4 border-bottom d-flex justify-content-between sticky-top">
        <form class="searching-area d-flex align-items-center gap-1 w-30" @submit.prevent="searchingAxios()">
          <label for="searching"><i class="bi bi-search"></i></label>
          <input id="searching" v-model="search.context" type="text" class="form-control border-0 bg-white" @submit="searchingAxios()" >
        </form>
        <ul class="list-group list-group-horizontal w-65 justify-content-between">
          <li class="list-group-item border-0 rankingsection w-40 d-flex justify-content-start gap-0" >
            <span class="rankingsection__title w-50" style="font-weight: 600; color: #0D6EFD"> <i class="bi bi-fire"></i> 실시간 검색어 순위</span>
            <span class="ms-3 rankingsection__keyword text-nowrap w-50">{{ranking.caroRank==null?"1. "+ranking.info[0].keyword:`${ranking.caroRank}. ${ranking.caroKey}`}}</span>
            <div class="rankingsection__board">
              <ul>
                <li><span class="rankingsection__title w-40" style="font-weight: 600;"> <i class="bi bi-fire"></i> 실시간 검색어 순위</span> <i @click="getRankingList" class="bi bi-arrow-clockwise" style="cursor: pointer;"></i></li>
                <li v-for="(info, idx) in ranking.info" :key="(info, idx)"><a :href="`/search?cards=${info.keyword}&order=new`">{{idx+1 +". "+ info.keyword}}</a></li>
              </ul>
            </div>
          </li>
          <li class="list-group-item border-0 alarm-icon">
            <div class="alarm-icon-badge d-flex justify-content-center align-items-center" style="background-color: #0D6EFD" v-if="$store.state.isAlarm>0">
                <span class="alarm-icon-badge_count">{{$store.state.isAlarm}}</span>
            </div>
            <AlarmPopover v-if="$store.state.token" class="text-wrap"></AlarmPopover>
          </li>
          <router-link to="/login" v-if="!$store.state.token">
            <li class="list-group-item border-0 navFont"  >LOGIN</li>
          </router-link>
          <router-link to="/logout" @click="logout()" v-if="$store.state.token">
            <li class="list-group-item border-0 navFont" >LOGOUT</li>
          </router-link>
          <router-link to="/join" v-if="!$store.state.token" >
            <li class="list-group-item border-0 navFont">JOIN</li>
          </router-link>
          <router-link to="/newpost">
            <li class="list-group-item border-0 navFont" v-if="$store.state.token">NEW ARTICLE</li>
          </router-link>
          <router-link :to="'/mypage?channel='+$store.state.userid">
            <li class="list-group-item border-0 navFont" v-if="$store.state.token" @click="mypage()">MY PAGE</li>
          </router-link>
          <router-link to="/setting" v-if="$store.state.token">
            <li class="list-group-item border-0 navFont">SETTING</li>
          </router-link>
        </ul>
      </div>
      <router-view />
    </div>
  </div>
  <QuestionModal />
</template>
<script>
import SubNavigationBar from "@/components/SubNavigationBar.vue";
import QuestionModal from "@/components/QuestionModal.vue";

import {reactive, onMounted} from "vue"
import { useRouter } from 'vue-router'
import store from "@/store";
import AlarmPopover from "@/components/AlarmPopover.vue";
import { useMeta } from "vue-meta";
import axios from "axios";


export default {
  name: "TopOfPage",
  components: {
    SubNavigationBar,
    QuestionModal,

    AlarmPopover
},
  setup(){
    const headers = {
        "Content-Type": "application/json; charset=utf-8",
    }

    const router = useRouter()

    const logout =()=>{
      store.commit('setUserId',0);
      store.commit('setToken',0);
      store.commit('setEmail',0);
      store.commit('setAlarm', 0);
      router.push({path:"/logout"})
    }

    let ranking = reactive({
      info:[],
      i:0,
      caroKey:null,
      caroRank:null,
    })

    let search = reactive({
      context:"",
    })
    function searchingAxios(){
      if (search.context.trim().length == 0){
        return
      }

      async function routing (){
        await router.push(`/search?cards=${search.context}&order=new`)
        await router.go(0)
      }
    routing();
    }
    const { meta } = useMeta({
                title:  ':: 에어리뷰',
            })
    function getRankingList(){
      axios.post(`${store.state.axiosLink}/ranking/list`, { headers }).then(function(res){
        ranking.info=[]
        ranking.info.push(...[...res.data])
      })
    }
    getRankingList()
    setInterval(getRankingList, 10000);
    onMounted(() => {
      setInterval(()=>{
      if(ranking.i==Math.floor(ranking.info.length/9*10)-1){
        ranking.i = 0
      }
      ranking.caroKey = ranking.info[ranking.i].keyword
      ranking.caroRank = ranking.i +1
      ranking.i++
    }, 3500)
    })
    async function mypage(){
      let id = new URLSearchParams(window.location.search).get("channel");
      await router.push("/mypage?channel="+store.state.userid);
      if (id == null){
        console.log("")
      } else if (id !== null){
        console.log("")
        await router.go(0)
      }
    }
    return{ searchingAxios, search, logout, meta, getRankingList, ranking, mypage}
  }


};
</script>

<style lang="sass">

@media(max-width:1200px)
  .content-area
    flex-direction: column

@media(min-width:1201px)
  .visually-hidden
    display: inline-block !important
body
  min-width: 1100px

#app::-webkit-scrollbar 
  display: hidden

.toggglesearching
  cursor:pointer

.alarm-icon
  position: relative
  .alarm-icon-badge
    position: absolute
    background-color: #3041a9
    color: white
    border-radius: 100%
    height: 32%
    width: 27%
    font-size: 0.5rem
    right: 0.3rem
    top: 0.3rem


.navFont
  font-family: MabinogiClassic

.rankingsection
  &:hover .rankingsection__board
    max-height: 30rem
    height: auto
.rankingsection__keyword
  text-overflow:ellipsis
  max-width: 5rem
  animation: 3.5s anim-lineUp ease-out infinite
  animation-delay: 7s

@keyframes anim-lineUp 
  0% 
    opacity: 0
    transform: translateY(30%)

  10% 
    opacity: 1
    transform: translateY(0%)

  50% 
    opacity: 1
    transform: translateY(0%)

  100% 
    opacity: 1
    transform: translateY(0%)
  
.rankingsection__board
  box-sizing: border-box
  max-height: 0
  overflow: hidden
  width: 95%
  background-color: white
  position: absolute
  padding: 0 1rem
  box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.25)  
  left:0
  transition: ease-in-out all 0.5s
  ul
    box-sizing: border-box
    display: flex
    flex-direction: column
    gap: 1rem
    padding: 1.5rem
    padding-top: 1rem
    list-style: none
    a
      color: inherit
</style>
