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
<!--        https://yoyostudy.tistory.com/43 참고-->
        <button type="button" class="btn btn-outline-primary w-100" @click="GoogleLoginBtn()">구글 로그인</button>
        <div id="my-signin2" style="display: none"></div>

        <div id="g_id_onload"
             data-client_id="YOUR_GOOGLE_CLIENT_ID"
             data-login_uri="https://your.domain/your_login_endpoint"
             data-auto_prompt="false">
        </div>
        <div class="g_id_signin"
             data-type="standard"
             data-size="large"
             data-theme="outline"
             data-text="sign_in_with"
             data-shape="rectangular"
             data-logo_alignment="left">
        </div>

      </div>

			<div class="utlpart d-flex justify-content-center align-items-center gap-3 my-3">
				<router-link to="/find"><span>아이디 · 비밀번호 찾기</span></router-link>
				<router-link to="/join"><span>회원가입</span></router-link>
			</div>
		</div>
	</Login>
</template>
<script>
import SiteLogin from "../components/SiteLogin.vue";
import { reactive } from "vue";
// import { gapi } from "gapi-script";
export default {
	components: { SiteLogin },
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

		return { state, changeform };
	},

  methods: {

    GoogleLoginBtn:function(){
      var self = this;

      window.gapi.signin2.render('my-signin2', {
        scope: 'profile email',
        width: 240,
        height: 50,
        longtitle: true,
        theme: 'dark',
        onsuccess: this.GoogleLoginSuccess,
        onfailure: this.GoogleLoginFailure,
      });

      setTimeout(function () {
        if (!self.googleLoginCheck) {
          const auth = window.gapi.auth2.getAuthInstance();
          auth.isSignedIn.get();
          document.querySelector('.abcRioButton').click();
        }
      }, 1500)

    },
    async GoogleLoginSuccess(googleUser) {
      const googleEmail = googleUser.getBasicProfile().getEmail();
      if (googleEmail !== 'undefined') {
        console.log(googleEmail);
      }
    },
    //구글 로그인 콜백함수 (실패)
    GoogleLoginFailure(error) {
      console.log(error);
    },
  }
};
</script>

<style lang=""></style>
