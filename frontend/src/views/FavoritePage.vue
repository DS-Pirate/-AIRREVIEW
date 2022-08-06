<template>
	<div class="mainviewpage">
    <h2>좋아요 글</h2>
		<div class="fav rounded-3 p-4 m-0 shadow-sm w-100 bg-white">
			<div class="row row-cols-2 gx-5 gy-3">
				<Cards v-for="i in 32" :key="i" class="col"> Column </Cards>
			</div>
		</div>
	</div>
</template>

<script>
// @ is an alias to /src

import Cards from "@/components/MainCards.vue";

export default {
	name: "FavoritePage",
	components: {
		Cards,
	},
};
</script>
