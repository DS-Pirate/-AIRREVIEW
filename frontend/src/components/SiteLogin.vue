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
        
    <!-- </form> -->
    </div>

</template>
<script>
import { reactive } from "vue";
import axios from 'axios';

export default {
    setup(){
        const state = reactive({
            form:{
                email:"",
                password:""
            }
        })

    const submit =()=>{
        axios.post("/airreview/api/account/login", state.form).then((res)=>{
        console.log(res);
        window.alert("로그인에 성공하셨습니다.");
        })
    }
        return{state, submit}
    }
}
</script>
<style lang="">
    
</style>