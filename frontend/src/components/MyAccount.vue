<template>
  <div class="myAccount p-5">
    <!-- 이미지 -->
    <div class="imgBox position-relative">
      <div class="picDiv position-absolute" type="file">
        <i class="bi bi-camera picIcon position-absolute"></i>
      </div>
      <img class="imgIcon" src="../assets/dummy.webp" />
    </div>
    <div class="nameDiv mt-2">
      <span class="fs-4 name">Jiyoung</span>
    </div>

    <!-- 폼 -->
    <form class="row g-3 mt-3 myAccountForm p-5">
      <div class="col-md-6 w-50">
        <label for="inputEmail4" class="form-label">이름</label>
        <input type="email" class="form-control" id="inputEmail4" />
      </div>
      <div class="col-md-6 w-50">
        <label for="inputEmail4" class="form-label">닉네임</label>
        <input type="email" class="form-control" id="inputEmail4" />
      </div>
      <div class="col-12">
        <label for="inputAddress" class="form-label">자기소개</label>
        <textarea
          class="form-control aboutMe"
          id="exampleFormControlTextarea1"
          rows="3"
        ></textarea>
      </div>
      <div class="col-md-2 w-20">
        <label for="inputState" class="form-label">생년월일</label>
          <select id="inputState" class="form-select col-md-2">
            <option selected>년도</option>
            <option v-for="i in 82" :key="i">{{ i + 1940 }}</option>
          </select>
      </div>

      <div class="col-md-2 w-20">
        <label for="inputState" class="form-label"> </label>
          <select id="inputState" class="form-select col-md-2 mt-2">
            <option selected>월</option>
            <option v-for="i in 12" :key="i">{{ i }}</option>
          </select>
      </div>
      <div class="col-md-2 w-20">
        <label for="inputState" class="form-label"> </label>
          <select id="inputState" class="form-select col-md-2 mt-2">
            <option selected>일</option>
            <option v-for="i in 31" :key="i">{{ i + 1940 }}</option>
          </select>
      </div>


      <div class="col-md-2 number w-20" >
        <label for="inputState" class="form-label">전화번호</label>
          <select id="inputState" class="form-select col-md-2" >
            <option selected>번호</option>
            <option>010</option>
            <option>011</option>
          </select>
      </div>
      <div class="col-md-2 number w-20" >
        <label for="inputState" class="form-label"></label>
          <input type="text" class="form-control col-md-2 mt-2" id="inputZip" />
      </div>
      <div class="col-md-2 number w-20" >
        <label for="inputState" class="form-label"></label>
          <input type="text" class="form-control col-md-2 mt-2" id="inputZip" />
      </div>

      <div class="col-md-6 w-50">
        <label for="inputPassword4" class="form-label">비밀번호</label>
        <input type="password" class="form-control" id="inputPassword4" />
      </div>
      <div class="col-md-6 w-50">
        <label for="inputPassword4" class="form-label">비밀번호 변경</label>
        <input type="password" class="form-control" id="inputPassword4" />
      </div>

      <div class="col-12">
        <div class="form-check form-switch">
          <input
            class="form-check-input"
            type="checkbox"
            role="switch"
            id="flesnwitchCheckChecked"
            checked
          />
          <label class="form-check-label" for="flesnwitchCheckChecked"
            >신규 구독자 알림</label
          >
        </div>
      </div>
      <div class="col-12">
        <div class="form-check form-switch">
          <input
            class="form-check-input"
            type="checkbox"
            role="switch"
            id="flesnwitchCheckChecked"
            checked
          />
          <label class="form-check-label" for="flesnwitchCheckChecked"
            >신규 팔로워 알림</label
          >
        </div>
      </div>
      <div class="col-12">
        <div class="form-check form-switch">
          <input
            class="form-check-input"
            type="checkbox"
            role="switch"
            id="flesnwitchCheckChecked"
            checked
          />
          <label class="form-check-label" for="flesnwitchCheckChecked"
            >새 댓글 알림</label
          >
        </div>
      </div>

      <div class="col-12">
        <button type="submit" class="btn btn-primary">수정하기</button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: "MyAccount",
  data() {
    return {};
  },
};
</script>
