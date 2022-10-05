<template>
	<Login class="d-flex justify-content-center align-items-center">
		<div class=" login p-5 ">
			<div class="loginlogo text-center"><img class="img-fluid" src="@/assets/airreviewlogo.png" alt="" srcset=""></div>
			

			<div class="loginmenu d-flex justify-content-center justalign-item-center w-100">
				<ul class="list-group list-group-horizontal w-100 px-5 my-4">
					<a v-on:click="changeform('false')" class="list-group-item w-50 border border-0 border-end text-end">아이디 찾기</a>
					<a v-on:click="changeform('true')"  class="list-group-item w-50 border border-0">비밀번호 찾기</a>
				</ul>
			</div>
			<div v-if="!(state.form==true)">
				<IdFind/>
			</div>
			<div v-else>
				<PassFind/>
			</div>
		</div>
	</Login>
</template>
<script>
import { reactive} from "vue";
import IdFind from "@/components/IdFind.vue";
import PassFind from "@/components/PassFind.vue";
import store from "@/store";
import router from "@/router";
export default {
	components: { IdFind, PassFind },

	setup() {
        let state = reactive({
            form: "true"
        })
        function changeform(a){
            if (a =="true"){
                state.form = true
                
            }else if(a=='false'){
                state.form = false
            }
            console.log(state.form);
        }
    function loginUser(){
      if (store.state.token){
        alert("잘못된 접근입니다.")
        router.push("/")
      }
    }
    loginUser();
		return {state, changeform};
	},
};
</script>
<style lang=""></style>
