<template>
	<div class="mainviewpage">
    <h2>저장된 글</h2>
		<div class="fav p-4 m-0 w-100 bg-white">
			<div class="row row-cols-2 gx-5 gy-3">
				<div v-for="(card, idx) in state.cards" :key="idx">
          <Cards :card="card"></Cards>
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


export default {
	name: "SavedPage",
	components: {Cards},
	setup() {
    const store = useStore();
		const state = reactive({
      cards: []
    })

		const url = "/airreview/api/save";
		const headers = {
            "Content-Type": "application/json; charset=utf-8",
            "Authorization": store.state.token,
            "userid" : store.state.userid
    }
		let body = {userid : store.state.userid}

    axios.post(url, body, { headers })
			.then((res) => {
				state.cards = res.data;
				console.log(res);
    })
    return {state}
	}
};
</script>
