<template>

    <div class="d-flex flex-column" action="login" method="post">
        <div class="input-group idsection px-5 my-2 ">
            <input class="form-control" type="text" name="id" id="id" v-model="state.form.email" required placeholder="아이디">    
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
import { useRouter } from 'vue-router';

export default {
  setup(){
    const router  = useRouter();
    const state = reactive({
      form:{
        email:"",
        password:""
      }
    })
    const submit =async()=>{
      if(state.email === ''){
        alert('아이디를 확인해주세요');
        state.form.email.value.focus();
        return false;
      } else if(state.pw === ''){
        alert('비밀번호를 확인해주세요');
        state.form.password.value.focus();
        return false;
      }

      console.log(`id : `+state.form.email+', passwd : '+state.form.password);
      //아직 하는 중!!!!
      const url     = "/airreview/api/"
      const headers = { "Content-Type": "application/json; charset=utf-8"}
      const body    = { e_mail: state.form.email, passwd: state.form.password }
      await axios.post(url, body, {headers}).then(function(res){
        if(res.data != null){
          sessionStorage.setItem("TOKEN", res.data.token)
          sessionStorage.setItem("e_mail", res.data.email)
          alert('로그인되었습니다.')
          console.log(res);
          router.push({name: "Login"})
        } else {
          console.log(res);
          alert('로그인 실패하였습니다.')
        }
      })



      // axios.post("/airreview/api/account/login", state.form).then((res)=>{
      // console.log(res);
      // window.alert("로그인에 성공하셨습니다.");
      // })
    }
    return{state, submit}
  }
}
</script>

<style lang="">
    
</style>