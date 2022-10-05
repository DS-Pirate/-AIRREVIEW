<template>
	<div class="mainviewpage">
    <h2>저장된 글</h2>
		<div class="fav p-4 m-0 w-100 bg-white">
      <div class="mb-4 mt-5 ms-4 ps-1 text-center" v-if="state.notSavedPage">
        <h1>저장된 글이 존재하지 않습니다</h1>
      </div>
			<div class="row row-cols-2 gx-5 gy-3">
				<div v-for="(card, idx) in state.cards" :key="idx">
          <Cards :name="state.usernames[idx]" :card="card"></Cards>
				</div>
			</div>
		</div>
	</div>
</template>



<script>
// @ is an alias to /src

import Cards from "@/components/SaveCards.vue";
import axios from "axios";
import { useStore } from 'vuex'
import {reactive} from "@vue/reactivity";
import router from "@/router";

export default {
	name: "SavedPage",
	components: {Cards},
	setup() {
    const store = useStore();
		const state = reactive({
      cards: [], usernames: [],
      notSavedPage: false
    })

		const url = store.state.axiosLink+"/api/save";
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
				console.log(res);
        if (state.cards.length == 0){
          state.notSavedPage = true;
        } else state.notSavedPage = false;
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
