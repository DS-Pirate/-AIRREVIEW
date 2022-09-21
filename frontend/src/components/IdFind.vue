<template>
	<form class="d-flex flex-column" action="login" method="post">
		<span class="answer text-center" id="answerForRecovery1">이메일</span>
		<div class="input-group idsection px-5 my-2">
			<input class="form-control" type="text" name="id" id="id" required placeholder="이메일" v-model="state.email" />
		</div>
		<div class="px-5 my-4">
			<button type="button" class="btn btn-outline-primary w-100" @click="submit()">아이디 찾기</button>
		</div>
	</form>
</template>
<script>
import {reactive, ref} from "@vue/reactivity";
import axios from "axios";

export default {
  setup() {
    const state = reactive({
      email: "",
    })
    const email = ref('')

    const submit = () => {
      if (state.email === '') {
        alert('이메일을 입력해주세요');
        email.value.focus();
        return false;
      } else if (!(state.email.includes("@") && state.email.includes("."))) {
        alert('이메일 양식이 맞지 않습니다.');
        email.value.focus();
        return false;
      }

      const url = './member/findemail'
      const headers = {
        "Content-Type": "application/json",
      }
      const body = {email : state.email};
      axios.post(url, body, {headers}).then(function (res){
        if(res.data != ''){
          console.log(typeof(res.data));
          alert('존재하는 이메일입니다!');
        } else {
          alert('존재하지 않는 이메일입니다');
        }
      })
    }
    return {state, submit}
   }
  };
</script>
<style lang=""></style>
