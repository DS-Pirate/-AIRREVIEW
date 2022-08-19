<template>
    <div class="myAccount">
        <!-- 이미지 -->
        <div class="imgBox position-relative">
            <div class="picDiv position-absolute" type="file">
                <i class="bi bi-camera picIcon position-absolute"></i>
            </div>
            <img class="imgIcon" src="../assets/dummy.webp">
        </div>
        <div class="nameDiv mt-2">
            <span class="fs-4 name">Jiyoung</span>
        </div>

        <form class="row g-3 mt-5">
            <div class="col-md-6">
                <label for="inputEmail4" class="form-label">이름</label>
                <input type="email" class="form-control" id="inputEmail4">
            </div>
            <div class="col-md-6">
                <label for="inputPassword4" class="form-label">닉네임</label>
                <input type="password" class="form-control" id="inputPassword4">
            </div>
            <div class="col-12">
                <label for="inputAddress" class="form-label">자기소개</label>
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
            </div>
            <div class="col-12">
                <label for="inputAddress2" class="form-label">Address 2</label>
                <input type="text" class="form-control" id="inputAddress2" placeholder="Apartment, studio, or floor">
            </div>
            <div class="col-md-6">
                <label for="inputCity" class="form-label">City</label>
                <input type="text" class="form-control" id="inputCity">
            </div>
            <div class="col-md-4">
                <label for="inputState" class="form-label">State</label>
                <select id="inputState" class="form-select">
                    <option selected>Choose...</option>
                    <option>...</option>
                </select>
            </div>
            <div class="col-md-2">
                <label for="inputZip" class="form-label">Zip</label>
                <input type="text" class="form-control" id="inputZip">
            </div>
            <div class="col-12">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="gridCheck">
                    <label class="form-check-label" for="gridCheck">
                        Check me out
                    </label>
                </div>
            </div>
            <div class="col-12">
                <button type="submit" class="btn btn-primary">Sign in</button>
            </div>
        </form>
    </div>
</template>

<script>
export default {
    name: 'MyAccount',
    data() {
        return {

        }
    }
};
</script>
