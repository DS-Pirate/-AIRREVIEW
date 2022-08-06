<template>

    <form class="d-flex flex-column" action="login" method="post">
        <div class="input-group idsection px-5 my-2 ">
            <input class="form-control" type="text" name="id" id="id" required placeholder="아이디">    
        </div>
        
        <div class="input-group passwordsection px-5 my-2 w-100">
            <input class="form-control" type="password" name="password" id="password" placeholder="비밀번호" required>    
        </div>
        <div class="px-5 my-4">
            <button type="button" class="btn btn-outline-primary w-100">로그인</button>
        </div>
        
    </form>

</template>
<script>
export default {

}
</script>
<style lang="">
    
</style>