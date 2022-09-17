<template>
	<form class="d-flex flex-column" action="login" method="post">
		<span class="text-center">이메일</span>
		<div class="input-group idsection px-5 my-2">
			<input class="form-control" type="text" name="id" id="id" v-model="state.email"  required placeholder="email" />
		</div>

    <span class="text-center" id="pwFindAnswer">비밀번호 찾기 질문</span>
    <div class="input-group idsection px-5 my-2">
      <select class="form-select" aria-label="Default select example" v-model="state.qtype" name="select" id="select">
<!--        <option selected>비밀번호 찾기 질문</option>-->
        <option value="q1">인상 깊게 읽은 책 이름은?</option>
        <option value="q2">나의 보물 1호는?</option>
        <option value="q3">기억에 남는 추억의 장소는?</option>
      </select>
    </div>

		<div class="input-group passwordsection px-5 my-2 w-100">
			<input class="form-control" type="password" name="password" id="password" v-model="state.answer" placeholder="답변" required />
		</div>
		<div class="px-5 my-4">
			<button type="button" class="btn btn-outline-primary w-100" @click="submit()">비밀번호 찾기</button>
		</div>
	</form>

  <div class="modal modal-signin position-flex d-block py-5 setpass" tabindex="-1" role="dialog" id="modalSignin" v-if="state.findid">
    <div class="modal-dialog" role="document" >
      <div class="modal-content rounded-4 shadow" >
        <div class="modal-header p-5 pb-4 border-bottom-0">
          <h2 class="mb-0 bold ">비밀번호 재설정</h2>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="close()"></button>
        </div>
        <div class="modal-body p-5 pt-0">
          <form class="">
            <div class="form-floating mb-3">
              <input type="password" class="form-control rounded-3" id="floatingInput" placeholder="새 비밀번호" v-model="state.pass">
              <label for="floatingInput">new password</label>
            </div>
            <div class="form-floating mb-3">
              <input type="password" class="form-control rounded-3" id="floatingPassword" placeholder="새 비밀번호재설정" v-model="state.repass">
              <label for="floatingPassword">repassword</label>
            </div>
            <button class="btn btn-outline-primary w-100" type="button" @click="setpass()">비밀번호 변경 확인</button>
          </form>
        </div>
      </div>
    </div>
  </div>



</template>
<script>
import {reactive, ref} from "@vue/reactivity";
import axios from "axios";
import router from "@/router";

export default {
  setup(){
    const state = reactive({
        email:"",
        qtype:"",
        answer:"",
        findid:"",
        q1:"",
        q2:"",
        q3:"",
      pass:'',
      repass:'',
    })
    const email = ref('')
    const qtype= ref('')
    const answer= ref('')
    const pass = ref('')
    const repass = ref('')


    //비밀번호 찾기 버튼
    const submit = () => {
      if (state.email === '') {
        alert('이메일을 입력해주세요');
        email.value.focus();
        return false;
      } else if (!(state.email.includes("@") && state.email.includes("."))) {
        alert('이메일 양식이 맞지 않습니다.');
        email.value.focus();
        return false;
      } else if (state.qtype === '') {
        alert('질문을 선택해주세요');
        qtype.value.focus();
        return false;
      } else if (state.answer === '') {
        alert('질문에 대한 답변을 입력해주세요');
        answer.value.focus();
        return false;
      } else if (state.qtype === 'q1'){
        state.q1 = state.answer
      } else if (state.qtype === 'q2'){
        state.q2 = state.answer
      } else if (state.qtype === 'q3'){
        state.q3 = state.answer
      }

      console.log(state.qtype);
      const url = './member/findpass'
      const headers = {
        "Content-Type": "application/json",
      }
      const body = {
        email: state.email,
        q1 : state.q1,
        q2 : state.q2,
        q3 : state.q3
      }
      axios.post(url, body, {headers}).then(function (res){
        if(res.data != null && typeof(res.data) != "string"){
          state.findid = res.data;
            console.log(typeof(res.data));
              alert('비밀번호 찾기에 성공하셨습니다.');
              console.log(state.findid);
        } else {
          alert('비밀번호 찾기에 실패하셨습니다!');
        }
      })
    }

    const close = () => {
      alert("비밀번호 변경이 취소되었습니다.");
      state.findid = '';
    }

    const setpass = () => {
      if (state.pass === '') {
        alert('새 비밀번호를 입력해주세요'); pass.value.focus(); return false;
      } else if (state.pass != state.repass) {
        alert('비밀번호가 일치하지 않습니다. 다시 입력해주세요');
        pass.value.value = ''; repass.value.value = '';
        state.repass.value.focus(); return false;
      }
      const url = './member/setpass'
      const headers = {
        "Content-Type": "application/json",
      }
      const bodyTwo = {
        userid: state.findid,
        passwd : state.pass,
      }

      axios.post(url, bodyTwo, {headers}).then(function (res){
        console.log(res.data);
        alert("비밀번호를 변경하였습니다.")
        router.push('/login');
      })
    }

    return {state, submit, close, setpass}
  }
};
</script>
<style lang=""></style>
