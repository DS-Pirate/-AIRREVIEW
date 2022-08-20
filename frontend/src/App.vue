<template>
	<div class="content-area container-fluid d-flex justify-content-between p-5 gap-3">
		<SubNavigationBar class="w-15" />
		<div class="routing-section w-85">
			<div class="routing-header mb-5 mt-4 border-bottom d-flex justify-content-between sticky-top">
				<form class="searching-area d-flex align-items-center gap-1 w-50" @submit.prevent="searchingAxios">
					<label for="searching"><i class="bi bi-search"></i></label>
					<input id="searching" v-model="search.context" type="text" class="form-control border-0 bg-white">
				</form>
				<ul class="list-group list-group-horizontal">
					<router-link to="/login">
						<li class="list-group-item border-0">Login</li>
					</router-link>
					<router-link to="/loout">
						<li class="list-group-item border-0">Logout</li>
					</router-link>
					<router-link to="/join">
						<li class="list-group-item border-0">Join</li>
					</router-link>
					<router-link to="/newpost">
						<li class="list-group-item border-0">New Article</li>
					</router-link>
					<router-link to="/mypage">
						<li class="list-group-item border-0">My Page</li>
					</router-link>
					<router-link to="/setting">
						<li class="list-group-item border-0">Setting</li>
					</router-link>
				</ul>
			</div>
			<router-view />
		</div>
	</div>
	<QuestionModal />
	<ShareModal />
</template>
<script>
import SubNavigationBar from "./components/SubNavigationBar.vue";
import QuestionModal from "./components/QuestionModal.vue";
import ShareModal from "./components/ShareModal.vue";
import {reactive} from "vue"
import { useRouter } from 'vue-router'

export default {
	name: "TopOfPage",
	components: {
		SubNavigationBar,
		QuestionModal,
		ShareModal
	},
	setup(){
		const router = useRouter()
		let search = reactive({
			context:""
		})
		function searchingAxios(){
			if (search.context.trim().length == 0){
				//prevent empty searching
				return
			}
			console.log("put axios function");
			router.push("/search")
		}

		return{ searchingAxios, search }
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
