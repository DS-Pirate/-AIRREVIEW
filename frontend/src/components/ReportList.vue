<template>
  <div class="admin-article d-flex flex-column align-items-center w-100">
    <h1>신고 관리</h1><br>
    <table class="table table-hover">
      <thead>
        <tr>
          <th scope="col">글 번호</th>
          <th scope="col">신고 내용</th>
          <th scope="col">신고 날짜</th>
          <th scope="col">삭제</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="info in state.reportlist" :key="info">
          <td scope="row">{{ info.reid }}</td>
          <td>{{ info.reportContext }}</td>
          <td>{{ info.regDate.split('T')[0] }}</td>
          <td><button @click="remove(info.reid)" class="btn btn-danger">삭제</button></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script setup>
import { reactive } from 'vue';
import axios from 'axios';
import store from '@/store';
import router from '@/router';

const state = reactive({
  reportlist: '',
})

const url = store.state.axiosLink+'/api/setting/reportlist'
    const headers = {
      "Content-Type": "application/json",
      "Authorization": store.state.token,
      "userid": store.state.userid
    }

axios.post(url, store.state.userid, { headers }).then(function (res) {
  state.reportlist = res.data;
  console.log(res);
})

function remove(num) {
  axios.post(store.state.axiosLink+'/api/setting/reportremove', num, { headers }).then(function (res) {
    res.data == true?alert("삭제되었습니다") : alert("실패하였습니다")
    router.go(0)
  })
}

</script>
<style scoped lang="sass">
a
    text-decoration: none
    color: inherit
thead, tbody
    text-align: center
tr, td
    max-width: 10rem
    line-height: 2.5rem
    vertical-align: middle
</style>