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
import store from "@/store";
import router from "@/router";

export default {
  setup(){
    const state = reactive({
      form:{
        email:"",
        password:"",
        name:"",
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
      const url = "/airreview/member/login"
      const headers = { "Content-Type": "application/json; charset=utf-8;"}
      const body = { email: state.form.email, passwd: state.form.password };
      await axios.post(url, body, {headers}).then(function(res){
        if(res.data != null){
          store.commit('setToken',res.data.token);
          store.commit('setEmail',res.data.email);
          store.commit('setUserId',res.data.userid);
          alert('로그인되었습니다.')
          router.push(`/`)
        } else {
          alert('로그인 실패하였습니다.')
        }
      })

    }
    return{state, submit}
  }
}
</script>

<style lang="">

</style>