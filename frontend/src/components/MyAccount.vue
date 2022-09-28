<template>
  <div class="myAccount p-5">
    <!-- 이미지 --> 
    <div class="imgBox position-relative">
      <div class="picDiv position-absolute">
        <form action="./setting/userimage" class="filebox" @change="submit($event)">
          <label for="ex_file" class="bi bi-camera picIcon position-absolute"></label>
          <input type="file" id="ex_file" />
        </form>
      </div>
      <img class="imgIcon" src="../assets/dummy.webp" />
    </div>
    <div class="nameDiv mt-2">
      <span class="fs-4 name">{{state.name}}</span>
    </div>

    <!-- 폼 -->
    <div class="row g-3 mt-3 myAccountForm pe-5 ps-5">
      <div class="col-md-3 w-50">
        <label for="inputEmail4" class="form-label">이름</label>
        <input type="name" class="form-control" id="inputEmail4" v-model="state.name" />
      </div>

      <div class="col-md-6 w-50">
        <label for="inputEmail4" class="form-label">이메일</label>
        <input type="email" class="form-control" id="inputEmail4" v-model="state.email" />
      </div>

      <div class="col-12">
        <label for="inputAddress" class="form-label">자기소개</label>
        <textarea class="form-control aboutMe" id="exampleFormControlTextarea1" rows="3"
          v-model="state.userintro"></textarea>
      </div>

      <div class="d-flex">
        <div class="col-md-2 w-15">
          <label for="inputState" class="form-label">생년월일</label>
          <select id="inputState" class="form-select" v-model="state.year">
            <option selected>{{state.year}}</option>
            <option v-for="i in 82" :key="i">{{ i + 1940 }}</option>
          </select>
        </div>

        <div class="col-md-2 w-15 mx-2">
          <label for="inputState" class="form-label"> </label>
          <select id="inputState" class="form-select mt-2" v-model="state.month">
            <option selected>{{state.month}}</option>
            <option v-for="i in 12" :key="i">{{ i }}</option>
          </select>
        </div>
        <div class="col-md-2 w-15">
          <label for="inputState" class="form-label"> </label>
          <select id="inputState" class="form-select mt-2" v-model="state.date">
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
import router from "@/router";
import { reactive } from "@vue/reactivity";
import axios from "axios";
import { useStore } from "vuex";

export default {
  name: "MyAccount",
  setup() {
    const state = reactive({
      userid: '',
      name: '',
      email: '',
      birthDay: '',
      year: '',
      month: '',
      date: '',
      userintro: '',
      cpasswd: '',
      upasswd: '',
    })
    const store = useStore();
    const url = store.state.axiosLink+'/api/setting/getuser'
    const headers = {
      "Content-Type": "application/json",
      "Authorization": store.state.token,
      "userid": store.state.userid
    }
    
    let body = {
      userid: store.state.userid,
    }

    async function a (){
    await axios.post(url, body, { headers }).then(function (res) {
      state.userid = res.data.userid;
      state.name = res.data.airName;
      state.email = res.data.email;
      state.birthDay = res.data.birthDay;
      state.userintro = res.data.userIntro;

      const regdate = new Date(Date.parse(res.data.birthDay))
      state.year = regdate.getFullYear();
      state.date = regdate.getDate();
      state.month = regdate.getMonth() + 1;
      console.log(regdate);

    })
    console.log(state.year);
    }
    a()

    const updatesetting = function () {

      const url2 = store.state.axiosLink+'/api/setting/changepasswd'

      // console.log(state.birthDay);

  

        axios.post(url2, state, { headers }).then(function (res) {
        alert(res.data)
        res.data == "다시 설정해주세요" ? "" : router.go(0)
      })
    }

    function submit(e) {
      let headers = {
        "Content-Type": "multipart/form-data",
        "Authorization": store.state.token,
        "userid": store.state.userid
      }
      let url = store.state.axiosLink+"/api/setting/userimage"
      let body = {data: e.target}
      console.log(e);
      axios.post(url, body, { headers })
    }

    return { state, updatesetting, submit }
  }
};
</script>
