                                                          <template>
	<form class="d-flex flex-column" action="login" method="post">
		<span class="text-center">아이디</span>
		<div class="input-group idsection px-5 my-2">
			<input class="form-control" type="text" name="id" id="id" v-model="state.email"  required placeholder="아이디" />
		</div>

    <span class="text-center" id="pwFindAnswer">비밀번호 찾기 질문</span>
    <div class="input-group idsection px-5 my-2">
      <select class="form-select"  @change="c" aria-label="Default select example" v-model="state.qtype" name="select" id="select">
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
</template>
<script>
import {reactive, ref} from "@vue/reactivity";
import axios from "axios";

export default {
  setup(){
    const state = reactive({
        email:"",
        qtype:"",
        answer:"",
        findemail:"",
        q1:"",
        q2:"",
        q3:"",
    })
    const email = ref('')
    const qtype= ref('')
    const answer= ref('')

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
        if(res.data != null){
          state.findemail = res.data.findemail;
        } else {
          alert('비밀번호 찾기에 실패하셨습니다!');
        }
      })
    }
    function c(e){console.log(e)}

    return {state, submit,c}
  }
};
</script>
<style lang=""></style>
