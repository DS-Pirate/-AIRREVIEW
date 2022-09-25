<template>
    <div class="d-flex justify-content-center align-items-center h-100">
        <h1>로그아웃에 성공하였습니다</h1>
    </div>
</template>
<script setup>
import {useRouter} from "vue-router/dist/vue-router";

const router = useRouter()
function home(){
  router.push(`/`);
}
setTimeout(home, 1000);

</script>
