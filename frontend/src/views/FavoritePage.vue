<template>
	<div class="mainviewpage">
    <h2>좋아요 글</h2>
		<div class="fav p-4 m-0 w-100 ">
      <div class="mb-4 mt-5 ms-4 ps-1 text-center" v-if="state.notFavCards">
        <h1>좋아요 글이 존재하지 않습니다</h1>
      </div>
      <div class="fav p-1">
			<div class="recentCardSection">
				<div v-for="(card, idx) in state.cards" :key="idx">
          <Cards :name="state.usernames[idx]" :card="card" :imageList="state.imageLists[idx]"></Cards>
				</div>
			</div>
		</div>
	</div>
</div>
</template>

<script>
// @ is an alias to /src

import Cards from "@/components/FavCards.vue";
import axios from "axios";
import { useStore } from 'vuex'
import {reactive} from "@vue/reactivity";
import router from "@/router";

export default {
	name: "FavPage",
	components: {Cards},
	setup() {
    const store = useStore();
		const state = reactive({
      cards: [], usernames: [], imageLists: [],
      notFavCards: false
    })

		const url = store.state.axiosLink+"/api/like";	
		const headers = {
            "Content-Type": "application/json; charset=utf-8",
            "Authorization": store.state.token,
            "userid" : store.state.userid
    }
		let body = {userid : store.state.userid}

    axios.post(url, body, { headers })
			.then((res) => {
				state.cards = res.data.card;
				state.usernames = res.data.username;
        state.imageLists = res.data.imageList;
        if (state.cards.length == 0){
          state.notFavCards = true;
        } else state.notFavCards = false;
    })

    function notloginUser(){
      if (!store.state.token){
        alert("잘못된 접근입니다.")
        router.push("/")
      }
    }
    notloginUser();
    return {state}
	}
};
</script>
<style scoped lang="sass">
  .recentCardSection
    display: grid
    grid-template-columns: repeat(auto-fit, minmax(450px, 1fr))
</style>