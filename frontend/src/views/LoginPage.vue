<template>
	<Login class="d-flex justify-content-center align-items-center">
		<div class="login  p-5 ">
			<div class="loginlogo text-center"><img class="img-fluid px-5" src="@/assets/airreviewlogo.png" alt="" srcset=""></div>
			

			<div class="loginmenu d-flex justify-content-center justalign-item-center w-100">
				<ul class="list-group list-group-horizontal w-100 px-5 my-4 overflow-hidden">
					<a v-on:click="changeform('false')"
						class="list-group-item w-100 border border-0 text-center">사이트 로그인</a>
				</ul>
			</div>
			<div v-if="!(state.form == true)">
				<SiteLogin></SiteLogin>
			</div>



			<div class="utlpart d-flex justify-content-center align-items-center gap-3 my-3">
				<router-link to="/find"><span>아이디 · 비밀번호 찾기</span></router-link>
				<router-link to="/join"><span>회원가입</span></router-link>
			</div>
		</div>
	</Login>
</template>
<script >
import SiteLogin from "../components/SiteLogin.vue";
import { reactive } from "vue";
import store from "@/store";
import router from "@/router";
export default {
	components: { SiteLogin},
  setup() {

		let state = reactive({
			form: "true"
		})
		function changeform(a) {
			if (a == "true") {
				state.form = true

			} else if (a == 'false') {
				state.form = false
			}
		}

    function loginUser(){
      if (store.state.token){
        alert("잘못된 접근입니다.")
        router.push("/")
      }
    }
    loginUser();

    return { state, changeform };
	},
};
</script>

<style lang=""></style>
