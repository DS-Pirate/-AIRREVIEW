<template>

    <form class="d-flex flex-column" action="join" method="post">
        <div class="input-group idsection px-5 my-2 ">
            <input class="form-control" type="text" name="id" id="id" required placeholder="아이디">    
        </div>
        
        <div class="input-group passwordsection px-5 my-2 w-100">
            <input class="form-control" type="password" name="password" id="password" placeholder="비밀번호" required>    
        </div>

        <div class="input-group passwordsection px-5 my-2 w-100">
            <input class="form-control" type="password" name="repassword" id="repassword" placeholder="비밀번호 재확인" required>    
        </div>

        <div class="input-group idsection px-5 my-2 ">
            <input class="form-control" type="text" name="name" id="name" required placeholder="이름">    
        </div>

        <div class="input-group idsection px-5 my-2 ">
            <input class="form-control" type="text" name="name" id="name" required placeholder="년">  
            <select class="form-select"  aria-label="Default select example" name="select" id="select">
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
            <input class="form-control" type="text" name="name" id="name" required placeholder="일">  
        </div>

        <div class="input-group idsection px-5 my-2 ">
            <select class="form-select"  aria-label="Default select example" name="select" id="select">
                <option selected>성별</option>
                <option value="1">남자</option>
                <option value="2">여자</option>
                <option value="3">선택 안함</option>
            </select>
        </div>

        <div class="input-group idsection px-5 my-2 ">
            <input class="form-control" type="text" name="email" id="eamil" required placeholder="본인 확인 이메일">    
        </div>

        <div class="input-group idsection px-5 my-2 d-flex justify-content-center justalign-item-center w-100">
            <input class="form-control" type="text" name="mobile" id="mobile" required placeholder="휴대전화">
            <button type="button" class="btn btn-outline-primary w-auto">인증번호 받기</button>
        </div>
        
        <div class="px-5 my-4">
            <button type="button" class="btn btn-outline-primary w-100">가입하기</button>
        </div>
        
    </form>

</template>
<script>
export default {

}
</script>
<style lang="">
    
</style>