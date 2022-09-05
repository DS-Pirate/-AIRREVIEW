<template>

    <div class="d-flex flex-column" action="login" method="post">
        <div class="input-group idsection px-5 my-2 ">
            <input class="form-control" type="text" name="id" id="id" v-model="state.form.email" required placeholder="아이디" >
        </div>
        
        <div class="input-group passwordsection px-5 my-2 w-100">
            <input class="form-control" type="password" name="password" id="password" v-model="state.form.password" placeholder="비밀번호" required>
        </div>
        <div class="px-5 my-4">
            <button @click="submit()" class="btn btn-outline-primary w-100">로그인</button>
        </div>

    </div>

</template>

<script charset='utf-8'>
import {reactive} from '@vue/reactivity';
import axios from 'axios';
// import { useRouter } from 'vue-router';

export default {
  setup(){
// const router = useRouter();
    const state = reactive({
      account: {
        userid:null,
        airName:"",
      },
      form:{
        email:"",
        password:""
      }
    })
    const submit =async()=>{
      if(state.form.email === ''){
        alert('아이디를 확인해주세요');
        return false;
      } else if(state.form.password === ''){
        alert('비밀번호를 확인해주세요');
        return false;
      }
      const url = "http://localhost:9090/airreview/member/login"
      const headers = { "Content-Type": "application/json; charset=utf-8; Access-Controle-Allow-Origin;"}
      const body = JSON.stringify({ eMail: state.form.email, passwd: state.form.password });
      console.log(body);
      axios.post(url,body,{ headers }).then((res)=>{
        alert("로그인에 성공하였습니다");
        state.account = res.data;
      }).catch(()=>{
        alert("로그인에 실패했습니다. 계정 정보를 확인해주세요!");
      })

      // axios.get(url, {headers}).then((res)=>{
      //   console.log(res.data);
      //   state.account = res.data;
      //
      // })

    }
    return{state, submit}
  }
}
</script>

<style lang="">
    
</style>