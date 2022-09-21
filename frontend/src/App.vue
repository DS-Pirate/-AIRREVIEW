<template>
  <metainfo /> 
  <div class="content-area container-fluid d-flex justify-content-between p-4 gap-3">
    <SubNavigationBar class="w-15 px-4"/>
    <div class="routing-section w-85">
      <div class="routing-header mb-5 mt-4 border-bottom d-flex justify-content-between sticky-top">
        <form class="searching-area d-flex align-items-center gap-1 w-50" @submit.prevent="searchingAxios">
          <label for="searching"><i class="bi bi-search"></i></label>
          <input id="searching" v-model="search.context" type="text" class="form-control border-0 bg-white" @keyup.enter="searchingAxios()">
        </form>
        <ul class="list-group list-group-horizontal">
          <li class="list-group-item border-0">
            <AlarmPopover v-if="$store.state.token"></AlarmPopover>
          </li>
          <router-link to="/login" v-if="!$store.state.token">
            <li class="list-group-item border-0" >Login</li>
          </router-link>
          <router-link to="/logout" @click="logout()" v-if="$store.state.token">
            <li class="list-group-item border-0" >Logout</li>
          </router-link>
          <router-link to="/join" v-if="!$store.state.token" >
            <li class="list-group-item border-0">Join</li>
          </router-link>
          <router-link to="/newpost">
            <li class="list-group-item border-0" v-if="$store.state.token">New Article</li>
          </router-link>
          <router-link to="/mypage">
            <li class="list-group-item border-0" v-if="$store.state.token">My Page</li>
          </router-link>
          <router-link to="/setting" v-if="$store.state.token">
            <li class="list-group-item border-0">Setting</li>
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

import {reactive} from "vue"
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
    const router = useRouter()

    const logout =()=>{
      store.commit('setUserId',0);
      store.commit('setToken',0);
      store.commit('setEmail',0);
      router.push({path:"/logout"})
    }

    let search = reactive({
      context:""
    })
    function searchingAxios(){
      if (search.context.trim().length == 0){
        //prevent empty searching
        return
      }
      console.log("put axios function");
      router.push(`/search?cards=${search.context}`)
      const searchUrl = window.location.href;
      const searchWord = searchUrl.split("/search?cards=")[1];
      console.log(searchWord);

      function getCardsInformation(){
        const url= `/airreview/article/search`
        const headers = {
          "Content-Type": "application/json; charset=utf-8",
        }
        const body = {
          search: searchWord
        }
        axios.post(url, body, {headers}).then(function (res){
          console.log(res.data);
          // state.cards = res.data;
        })
      }
      getCardsInformation()
    }
    const { meta } = useMeta({
                title:  ':: 에어리뷰',
            })

    return{ searchingAxios, search, logout,meta}
  }


};
</script>

<style>
@media(max-width:1200px){
  .content-area{
    flex-direction: column;
  }
}

@media(min-width:1201px){
  .visually-hidden{
    display: inline-block !important;
  }


}
body{
  min-width: 1100px;
}

#app::-webkit-scrollbar {
  display: hidden;

}
.toggglesearching{
  cursor:pointer;

}

</style>
