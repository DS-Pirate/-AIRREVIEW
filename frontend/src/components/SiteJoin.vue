<template>
    <div class="d-flex flex-column">
        <div class="input-group idsection px-5 my-2">
            <input ref="email" class="form-control" type="text" name="id" id="id" v-model="state.email" @change="state.emailCheck=false" placeholder="이메일" required>
          <button class="btn btn-outline-primary w-25" @click="check()">중복 확인</button>  
        </div> 
        <div class="input-group idsection px-5 my-2" v-if="state.emailCheck">
          <input class="form-control" type="text" :disabled="state.authCheck==2" v-model="state.authNum">
          <button class="btn btn-outline-primary w-25" @click="reqAuthNum()" v-if="state.authCheck==0" style="white-space:nowrap;">인증 번호 받기</button>  
          <button class="btn btn-outline-primary w-25" @click="checkAuthNum()" v-if="state.authCheck==1" style="white-space:nowrap;">인증 번호 확인</button>
          <button class="btn btn-outline-primary w-25" v-if="state.authCheck==2" style="white-space:nowrap; not-allowed: not-allowed;">인증 완료</button>
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
            <input class="form-control" type="text" name="name" id="year" v-model="state.year" placeholder="년" required>
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
            <input class="form-control" type="text" name="name" id="day" v-model="state.day" required placeholder="일">
        </div>

        <div class="input-group idsection px-5 my-2">
            <select class="form-select" aria-label="Default select example"  v-model="state.gender" name="select" id="select">
                <option selected>성별</option>
                <option value="1">남자</option>
                <option value="2">여자</option>
                <option value="3">하남자</option>
            </select>
        </div>

        <div class="input-group idsection px-5 my-2">
            <input class="form-control" type="text" name="email" id="q1" v-model="state.q1"  required placeholder="인상 깊게 읽은 책 이름은??">
        </div>

        <div class="input-group questionsection px-5 my-2">
            <input class="form-control" type="text" name="email" id="q2" v-model="state.q2" required placeholder="나의 보물 1호는?">
        </div>
        
        <div class="input-group questionsection px-5 my-2">
            <input class="form-control" type="text" name="email" id="q3" v-model="state.q3" required placeholder="기억에 남는 추억의 장소는?">
        </div>
        
        <div class="px-5 my-4">
            <button class="btn btn-outline-primary w-100" @click="submit()">가입하기</button>
        </div>
        
    </div>

</template>


<script>
import { reactive, ref } from '@vue/reactivity'
import axios from 'axios'
import router from "@/router";
import store from "@/store";

export default {
  name: 'SiteJoin',
  setup(){
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
      authNum:'',
      showModal: false,
      emailCheck: false,
      authCheck : 0
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

    const headers = {
      "Content-Type": "application/json",
    }
    function reqAuthNum(){
      axios.post(store.state.axiosLink+"/email/emailauth", {email : state.email},{ headers })
      .then(function(res){
        if (res.data == true) {
          alert("해당이메일로 발송되었습니다")
          state.authCheck = 1
          state.authNum=""
        }
      })
    }

    function checkAuthNum(){
      axios.post(store.state.axiosLink+"/email/emailcompare", {email : state.email, authNum: state.authNum},{headers})
      .then(function(res){
        if(res.data =="인증이 완료되었습니다 1시간 이내에 가입을 완료해주세요"){
          state.authCheck=2
          alert(res.data)
        }else if(res.data == "잘못된 접근입니다"){
          router.push("/")
        }else{
          alert(res.data)
        }
      })
    }

    //email check
    const check = async () => {
      if (state.email === '') {
        alert('이메일을 입력해주세요.'); email.value.focus(); return false;
      } else if (!(state.email.includes("@") && state.email.includes("."))) {
        alert('이메일 양식이 맞지 않습니다.'); email.value.focus(); return false;
      }

      const url = store.state.axiosLink+'/member/findemail'
      const body = {email : state.email};
      axios.post(url, body, {headers}).then(function (res){
        if(res.data != ''){
          alert('이미 존재하는 이메일입니다.');
        } else {
          alert('사용 가능한 이메일입니다.');
          state.emailCheck =  true;
        }
      })
    }


    //join
    const submit = async () => {
      if (state.emailCheck === false) {
        alert('이메일 중복 확인 버튼을 눌러주세요'); email.value.focus(); return false;
      } else
        if (state.password === '') {
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
      } else if (state.authCheck !=2){
        alert('이메일 인증이 필요합니다.')
        return false;
      }

      const birthDay = new Date(state.year, state.month, state.day);
      const url = store.state.axiosLink+'/member/register'
      const headers = {
        "Content-Type": "application/json",
      }
      const body = {
        email: state.email, passwd: state.password, airName: state.name, birthDay: birthDay,
        gender: state.gender, q1: state.q1, q2: state.q2, q3: state.q3
      };
      const response = await axios.post(url, body, { headers })
      if (response.status == 200) {
        alert('회원가입이 완료되었습니다.')
        router.push(`/login`)

      } else {
        alert(response.data)
      }
    }

    function loginUser(){
      if (store.state.token){
        alert("잘못된 접근입니다.")
        router.push("/")
      }
    }
    loginUser();

    return {state, submit, check, reqAuthNum, checkAuthNum}
  }

}
</script>



<style lang="">
    
</style>