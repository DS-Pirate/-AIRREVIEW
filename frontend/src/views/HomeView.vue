<template>
	<div class="mainviewpage">
		<div class="fav p-1">
			<div  class="row row-cols-3">
				<Cards :cardinfo="i" v-for="i in 25" :key="i"></Cards>
			</div>
		</div>
	</div>
</template>

<script setup>
import Cards from "@/components/MainCards.vue";
sessionStorage.clear()

</script>
