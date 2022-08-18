<template>
	<div class="content-area container-fluid d-flex justify-content-between p-5 gap-3">
		<SubNavigationBar class="w-15" />
		<div class="routing-section w-85">
			<div class="routing-header mb-5 mt-4 border-bottom d-flex justify-content-between sticky-top">
				<div class="searching-area d-flex align-items-center gap-1 w-50">
					<label for="searching"><i class="bi bi-search toggglesearching "></i></label>
					<input id="searching" type="text" class="form-control border-0 bg-white">
				</div>
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

export default {
	name: "TopOfPage",
	components: {
		SubNavigationBar,
		QuestionModal,
		ShareModal
	},
	setup(){
		return{  }
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
