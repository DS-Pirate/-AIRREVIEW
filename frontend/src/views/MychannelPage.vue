<template>
	<channelpage>
		<div class="chimg  p-3 m-0 shadow-sm bg-white">
			<img src="@/assets/longdummy2.jpeg" class="ch_img w-100" alt="" />
		</div>
		<div class="chcategory  p-1 my-3 shadow-sm bg-white d-flex justify-content-center">
			<ul class="list-group list-group-horizontal h-10 align-items-center">
				<li class="list-group-item border border-0" v-on:click="change('list')">글 목록</li>
				<span>|</span>
				<li class="list-group-item border border-0" v-on:click="change('category')">구독 정보</li>
				<span>|</span>
				<li class="list-group-item border border-0" v-on:click="change('channelinfo')">내 정보</li>
			</ul>
		</div>
		<div class="chcategory  p-3 my-3 shadow-sm bg-white d-flex h-50" v-if="(state.form == 'list')">
			<span>list</span>
		</div>
		<div class="chcategory  p-3 my-3 shadow-sm bg-white d-flex h-50" v-else-if="(state.form == 'category')">
			<span>cate</span>
		</div>
		<div class="chcategory  p-3 my-3 shadow-sm bg-white d-flex h-50" v-else-if="(state.form == 'channelinfo')">
			<span>info</span>
		</div>
	</channelpage>
</template>ƒ
<script>
import { reactive } from "vue";

export default {
	components: { },

	setup() {
		let state = reactive({
			form: "list",
		});
		function change(which) {
			state.form = which;
			console.log(state.form);
		}
		return { state, change };
	},
};
</script>
