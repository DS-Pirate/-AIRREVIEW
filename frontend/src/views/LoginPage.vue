<template>
	<Login class="d-flex justify-content-center align-items-center">
		<div class="login  p-5 ">
			<div class="loginlogo text-center"><img class="img-fluid px-5" src="@/assets/airreviewlogo.png" alt="" srcset=""></div>
			

			<div class="loginmenu d-flex justify-content-center justalign-item-center w-100">
				<ul class="list-group list-group-horizontal w-100 px-5 my-4 overflow-hidden">
					<a v-on:click="changeform('false')"
						class="list-group-item w-50 border border-0 border-end text-end">사이트 로그인</a>
					<a v-on:click="changeform('true')" class="list-group-item w-50 border border-0">구글 로그인</a>
				</ul>
			</div>
			<div v-if="!(state.form == true)">
				<SiteLogin></SiteLogin>
			</div>
			<div v-else>
<!--     <button type="button" class="btn btn-outline-primary w-100" :callback="callback" :buttonConfig="buttonConfig">구글 로그인</button>-->
<!--버튼 크기 문제↓ㅠㅠㅠㅠ-->
        <GoogleLogin
            class="w-100"
            :callback="callback"
            :buttonConfig="buttonConfig"/>


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
import { decodeCredential } from "vue3-google-login";
import { reactive } from "vue";
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
			console.log(state.form);
		}

    //google
    const callback = (response) => {
      const userData = decodeCredential(response.credential);
      console.log("Handle the response", response);
      console.log("DecodeCredential", userData);
    };

    const buttonConfig = {
      type: "standard",
      theme: "outline",
      size: "large",
      text: "signup_with",
      // shape: "",
      // logo_alignment: "",
      // width: "",
      // locale: "",
    };




		return { state, changeform, callback, buttonConfig };
	},
};
</script>

<style lang=""></style>
