<template>
	<div class="mainviewpage">
    <h2>최근 올라온 글</h2>
		<div class="fav rounded-3 p-3 m-0 shadow-sm w-100 bg-white">
			<div class="row row-cols-3 gx-4 gy-3">
				<Cards v-for="i in 32" :key="i" class="col"> Column </Cards>
			</div>
		</div>
	</div>
</template>

<script>
// @ is an alias to /src

import Cards from "@/components/MainCards.vue";

export default {
	name: "HomeView",
	components: {
		Cards,
	},
};
</script>
