<template>
    <div class="d-flex flex-column">
        <div class="input-group idsection px-5 my-2">
            <input class="form-control" type="text" name="id" id="id" v-model="state.email"  placeholder="아이디" required>
        </div>
        
        <div class="input-group passwordsection px-5 my-2 w-100">
            <input class="form-control" type="password" name="password" id="password" v-model="state.password" placeholder="비밀번호" required>
        </div>

        <div class="input-group passwordsection px-5 my-2 w-100">
            <input class="form-control" type="password" name="repassword" id="repassword" v-model="state.repassword" placeholder="비밀번호 재확인" required>
        </div>

        <div class="input-group idsection px-5 my-2">
            <input class="form-control" type="text" name="name" id="name" v-model="state.name" placeholder="이름" required>
        </div>

        <div class="input-group idsection px-5 my-2">
            <input class="form-control" type="text" name="name" id="name" v-model="state.year" placeholder="년" required>
            <select class="form-select"  aria-label="Default select example" v-model="state.month" name="select" id="select">
                <option selected>월</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
            </select>
            <input class="form-control" type="text" name="name" id="name" v-model="state.day" required placeholder="일">
        </div>

        <div class="input-group idsection px-5 my-2">
            <select class="form-select" aria-label="Default select example"  v-model="state.gender" name="select" id="select">
                <option selected>성별</option>
                <option value="1">남자</option>
                <option value="2">여자</option>
                <option value="3">상여자</option>
                <option value="4">하남자</option>
                <option value="5">선택 안함</option>
            </select>
        </div>

        <div class="input-group idsection px-5 my-2">
            <input class="form-control" type="text" name="email" id="eamil" v-model="state.q1"  required placeholder="인상 깊게 읽은 책 이름은??">
        </div>

        <div class="input-group questionsection px-5 my-2">
            <input class="form-control" type="text" name="email" id="eamil" v-model="state.q2" required placeholder="나의 보물 1호는?">
        </div>
        
        <div class="input-group questionsection px-5 my-2">
            <input class="form-control" type="text" name="email" id="eamil" v-model="state.q3" required placeholder="기억에 남는 추억의 장소는?">
        </div>
        
        <div class="px-5 my-4">
            <button class="btn btn-outline-primary w-100" @click="submit()">가입하기</button>
        </div>
        
    </div>

</template>
<script>
import { reactive, ref } from '@vue/reactivity'
// import { onMounted } from 'vue';
import axios from 'axios'
// import { useRouter } from 'vue-router';

export default {
  name: 'SiteJoin',
  setup(){
    // const router = useRouter();
    const state = reactive({
      email: '',
      password: '',
      repassword: '',
      name: '',
      year: '',
      month: '',
      day: '',
      gender: '',
      q1: '',
      q2: '',
      q3: '',

      token: sessionStorage.getItem("TOKEN"),
      showModal: false,
    })

    const email = ref('')
    const password = ref('')
    const repassword = ref('')
    const name = ref('')
    const year = ref('')
    const month = ref('')
    const day = ref('')
    const gender = ref('')
    const q1 = ref('')
    const q2 = ref('')
    const q3 = ref('')


    //회원가입 버튼
    const submit = async () => {
      if (state.email === '') {
        alert('이메일을 입력해주세요'); email.value.focus(); return false;
      } else if (state.password === '') {
        alert('비밀번호를 입력해주세요'); password.value.focus(); return false;
      } else if (state.repassword === '') {
        alert('비밀번호를 입력해주세요'); repassword.value.focus(); return false;
      } else if (state.name === '') {
        alert('이름을 입력해주세요'); name.value.focus(); return false;
      } else if (state.year === '') {
        alert('생일을 입력해주세요'); year.value.focus(); return false;
      } else if (state.month === '') {
        alert('생일을 입력해주세요'); month.value.focus(); return false;
      } else if (state.day === '') {
        alert('생일을 입력해주세요'); day.value.focus(); return false;
      } else if (state.gender === '') {
        alert('성별을 입력해주세요'); gender.value.focus(); return false;
      } else if (state.q1 === '') {
        alert('확인질문을 입력해주세요'); q1.value.focus(); return false;
      }  else if (state.q2 === '') {
        alert('확인질문을 입력해주세요'); q2.value.focus(); return false;
      } else if (state.q3 === '') {
        alert('확인질문을 입력해주세요'); q3.value.focus(); return false;
      } else if (state.password != state.repassword) {
        alert('비밀번호가 일치하지 않습니다. 다시 입력해주세요');
        password.value.value = ''; repassword.value.value = '';
        password.value.focus(); return false;
      }

      const birthDay = new Date(state.year, state.month, state.day);
      const url = '/airreview/api/member/register'
      const headers = {
        "Content-Type": "application/json",
        Authorization: state.token,
        "token": state.token
      }
      const body = {
        eMail: state.email, passwd: state.password, airName: state.name, birthDay: birthDay,
        gender: state.gender, q1: state.q1, q2: state.q2, q3: state.q3

      }
      const response = await axios.post(url, body, { headers })
      console.log(response.data)
      if (response.status === 200) {
        alert('회원가입이 되었습니다.');
      } else {
        alert('회원가입에 실패하였습니다.')
      }
    }

    return {state, submit}
  }

}
</script>
<style lang="">
    
</style>