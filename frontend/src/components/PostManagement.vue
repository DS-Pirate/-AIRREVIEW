<template>
  <div class="admin-article d-flex flex-column  w-100">
    <h1>글 관리</h1><br>
    <table class="table table-hover">
      <thead>
        <tr>
          <th scope="col">글 번호</th>
          <th scope="col">글 제목</th>
          <th scope="col">좋아요 수</th>
          <th scope="col">저장 수</th>
          <th scope="col">조회수</th>
          <th scope="col">작성일</th>
          <th scope="col">수정</th>
          <th scope="col">삭제</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="info in state.articlelist" :key="info">
          <td scope="row">{{ info.aid }}</td>
          <td>{{ info.atitle }}</td>
          <td>{{ info.count }}</td>
          <td>{{ info.savecount }}</td>
          <td>{{ info.opencount }}</td>
          <td>{{ info.regdate.split('T')[0] }}</td>
          <td><button @click="modify(info.aid)" class="btn btn-primary">수정</button></td>
          <td><button @click="remove(info.aid)" class="btn btn-danger">삭제</button></td>
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
  articlelist: '',
})

const url = store.state.axiosLink+'/api/setting/articlelist'
    const headers = {
      "Content-Type": "application/json",
      "Authorization": store.state.token,
      "userid": store.state.userid
    }
  
  axios.post(url, store.state.userid, { headers }).then(function (res) {
    state.articlelist = res.data.article;
    console.log(res);
  })

  function remove(num) {
  axios.post(store.state.axiosLink+'/api/setting/articleremove', num, { headers }).then(function (res) {
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