<template>
  <div class="subnav">
    <div class="navbar container-fluid d-flex flex-column justify-content-center align-items-start sticky-top">
      <section class="navlogo">
        <router-link to="/"><h1 class="mb-5 fw-bold my-2"  style="font-family: PyeongChangPeace-Bold; font-size: 35px;" @click="logo()">AIR
          <br>REVIEW</h1></router-link>
      </section>
      <div class="navarea" v-if="toggle.nav =='true'">
        <div class="navbody my-2">
          <ul class="list-group list-group-flush">
            <router-link to="/"><li class="list-group-item my-1" :class="{btnclick: state.home === true }" @click="btnclick(true,false,false,false);scrollToTop()">HOME</li> </router-link>
            <router-link to="/fav"><li class="list-group-item my-1" v-if="$store.state.token" :class="{btnclick: state.fav === true }" @click="btnclick(false,true,false,false)">FAVORITE</li></router-link>
            <router-link to="/save"><li class="list-group-item my-1" v-if="$store.state.token" :class="{btnclick: state.saved === true }" @click="btnclick(false,false,true,false)">SAVED</li></router-link>
            <router-link to="/recent"><li class="list-group-item my-1" v-if="$store.state.token" :class="{btnclick: state.recently === true }" @click="btnclick(false,false,false,true)">RECENTLY</li></router-link>
            <li class="list-group-item my-1" data-bs-toggle="modal" data-bs-target="#question" v-if="$store.state.token">1:1 Q&A</li>
            <li class="list-group-item my-1" data-bs-toggle="offcanvas" data-bs-target="#subscribe" aria-controls="offcanvasWithBothOptions" v-if="$store.state.token">SUBSCRIBE</li>
          </ul>
        </div>
        <div class="navfooter mt-5">
          <h6>Proeject by Team Pirate</h6>
          <h6><a href="https://github.com/DS-Pirate" class="bi bi-github">&nbsp;Project repository</a></h6>
          <h6><i class="bi bi-funnel-fill"></i>&nbsp;Busan, Korea</h6>
          <hr style="opacity: 0.8;">
          <h6><a href="https://github.com/B-HS" class="bi bi-github">&nbsp;HYUNSEOK BYUN</a></h6>
          <h6><a href="https://github.com/sjiyeong12" class="bi bi-github">&nbsp;JIYEONG SONG</a></h6>
          <h6><a href="https://github.com/Kim-DH0" class="bi bi-github">&nbsp;DOHYEON KIM</a></h6>
        </div>
      </div>
      <button class="bi bi-list  btn btn-primary" v-else-if="toggle.nav =='false'" data-bs-toggle="offcanvas" data-bs-target="#navbarr" aria-controls="offcanvasScrolling"></button>
      <div class="navarea offcanvas offcanvas-bottom offheights border-0 minforoff"  data-bs-scroll="true" data-bs-backdrop="false"  id="navbarr" aria-labelledby="navbarr">
        <div class="navbody border-0 d-flex justify-content-between align-items-center">
          <ul class="list-group list-group-flush list-group-horizontal fs-6 2 border-0">
            <router-link to="/"><li class="list-group-item my-1 2 border-0 text-end">HOME</li></router-link>
            <router-link to="/fav"><li class="list-group-item my-1 text-end" v-if="$store.state.token">FAVORITE</li></router-link>
            <router-link to="/save"><li class="list-group-item my-1 text-end" v-if="$store.state.token">SAVED</li></router-link>
            <router-link to="/recent"><li class="list-group-item my-1 text-end" v-if="$store.state.token">RECENTLY</li></router-link>
            <li class="list-group-item my-1 text-end" data-bs-toggle="modal " data-bs-target="#question" v-if="$store.state.token">1:1 Q&A</li>
            <li class="list-group-item my-1 text-end" data-bs-toggle="offcanvas" data-bs-target="#subscribe" aria-controls="offcanvasWithBothOptions" v-if="$store.state.token">SUBSCRIBE</li>
          </ul>
          <button type="button" width="15px" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>

        </div>
      </div>
    </div>
    <SubscribeOffcanvas />
  </div>
</template>
<script>
import SubscribeOffcanvas from './SubscribeOffcanvas.vue';
import {reactive, onMounted} from 'vue'
import router from "@/router";
export default {
  components: { SubscribeOffcanvas },
  setup(){
    const state = reactive({
      home : false,
      fav : false,
      saved : false,
      recently : false,
    })
    onMounted(() => {
      if (window.innerWidth <1200){
        toggle.nav = "false"
      }
      window.addEventListener('resize', ()=>{
        if (window.innerWidth<1200){
          toggle.nav = "false"
        }else{
          toggle.nav = "true"
        }
      })
    })

    function btnclick(home, fav, saved, recently){
      state.home = home;
      state.fav = fav;
      state.saved = saved;
      state.recently = recently;
    }

    const toggle=reactive({
      nav:"true"
    })
    async function logo(){
    await router.push("/");
    await btnclick(false,false,false,false);
    await router.go(0);
    }

    function scrollToTop() {
        window.scrollTo(0,0);
    }
    return{ toggle, state,btnclick,logo,scrollToTop }
  }
};
</script>
<style scoped>
.navfooter{
  color: rgba(200, 200, 200);

}
.navfooter a{
  text-decoration: none;
  color: rgba(200, 200, 200, 0.9);
  transition: all ease-in-out 0.3s;
}
h6{
  font-size: 0.7rem;
  transition: all ease-in-out 0.3s;
}

.navfooter a:hover,
h6:hover{
  color: black;
}
.offheights{
  height: 3.5vh !important;
}
.navbar .list-group-item, .navbar .offcanvas{
  background-color: none !important;
}
.minforoff{
  min-width: 750px;
  overflow: scroll;
}
.minforoff::-webkit-scrollbar{
  display: none;
}


.btnclick{
  color: #0D6EFD;
  font-weight: 700;
  }

</style>
