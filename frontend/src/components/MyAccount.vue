<template>
  <div class="myAccount p-5">
    <!-- 이미지 -->
    <div class="imgBox position-relative">
      <div class="picDiv position-absolute">
        <div class="filebox">
          <label for="ex_file" class="bi bi-camera picIcon position-absolute"></label>
          <input type="file" id="ex_file" />
        </div>
      </div>
      <img class="imgIcon" src="../assets/dummy.webp" />
    </div>
    <div class="nameDiv mt-2">
      <span class="fs-4 name">{{state.username}}</span>
    </div>

    <!-- 폼 -->
    <div class="row g-3 mt-3 myAccountForm pe-5 ps-5">
      <div class="col-md-3 w-50">
        <label for="inputEmail4" class="form-label">이름</label>
        <input type="name" class="form-control" id="inputEmail4" :value="state.username" />
      </div>

      <div class="col-md-6 w-50">
        <label for="inputEmail4" class="form-label">이메일</label>
        <input type="email" class="form-control" id="inputEmail4" :value="state.email" />
      </div>

      <div class="col-12">
        <label for="inputAddress" class="form-label">자기소개</label>
        <textarea class="form-control aboutMe" id="exampleFormControlTextarea1" rows="3"
          :value="state.userintro"></textarea>
      </div>

      <div class="d-flex">
        <div class="col-md-2 w-15">
          <label for="inputState" class="form-label">생년월일</label>
          <select id="inputState" class="form-select" :value="state.year">
            <option selected>{{state.year}}</option>
            <option v-for="i in 82" :key="i">{{ i + 1940 }}</option>
          </select>
        </div>

        <div class="col-md-2 w-15 mx-2">
          <label for="inputState" class="form-label"> </label>
          <select id="inputState" class="form-select mt-2">
            <option selected>{{state.month}}</option>
            <option v-for="i in 12" :key="i">{{ i }}</option>
          </select>
        </div>
        <div class="col-md-2 w-15">
          <label for="inputState" class="form-label"> </label>
          <select id="inputState" class="form-select mt-2">
            <option selected>{{state.date}}</option>
            <option v-for="i in 31" :key="i">{{ i }}</option>
          </select>
        </div>
      </div>

      <div class="col-md-3 w-50">
        <label for="inputPassword4" class="form-label">현재 비밀번호</label>
        <input type="password" class="form-control" id="inputPassword4" v-model="state.cpasswd" />
      </div>
      <div class="col-md-6 w-50">
        <label for="inputPassword4" class="form-label">변경할 비밀번호</label>
        <input type="password" class="form-control" id="inputPassword4" v-model="state.upasswd" />
      </div>
      <div class="col-12">
        <button class="btn btn-primary" @click="updatesetting()">수정하기</button>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from "@vue/reactivity";
import axios from "axios";
import { useStore } from "vuex";

export default {
  name: "MyAccount",
  setup() {
    const state = reactive({
      username: '',
      email: '',
      birthday: '',
      userintro: '',
      cpasswd: "",
      upasswd: ""
    })



    const store = useStore();
    const url = './api/setting/getuser'
    const headers = {
      "Content-Type": "application/json",
      "Authorization": store.state.token,
      "userid": store.state.userid
    }

    let body = {
      userid: store.state.userid,
      cpasswd: state.cpasswd,
      upasswd: state.upasswd
    }

    axios.post(url, body, { headers }).then(function (res) {
      state.username = res.data.airName;
      state.email = res.data.email;
      state.birthday = res.data.birthday;
      state.userintro = res.data.userIntro;

      const regdate = new Date(Date.parse(res.data.birthDay))
      state.year = regdate.getFullYear();
      state.date = regdate.getDate();
      state.month = regdate.getMonth() + 1;
      console.log(res.data);
    })

    const updatesetting = function () {

      const url2 = './api/setting/changePasswd'

      let body2 = {
        userid: store.state.userid,
        cpasswd: state.cpasswd,
        upasswd: state.upasswd,
        name: state.username,
        email: state.email,
        userintro: state.userintro,
        birthday: state.birthday
      }

      axios.post(url2, body2, { headers }).then(function (res) {
        console.log(res);
      })
    }


    return { state, updatesetting }
  }
};
</script>
