<template>
  <!-- <form class="d-flex flex-column w-100" action="postmanagement" method="post">
<h2>글 관리</h2>
<table class="w-100 table table-hover success">
  <thead>
    <tr class="table-white">
      <th scope="col">글 번호</th>
      <th scope="col">글 제목</th>
      <th scope="col">글 내용</th>
      <th scope="col">좋아요</th>
      <th scope="col">싫어요</th>
      <th scope="col">작성일</th>
      <th scope="col">수정</th>
      <th scope="col">삭제</th>
    </tr>
  </thead>
  <tbody class="table-group-divider">
    <tr>
      <th scope="row">3</th>
      <td>{{state.title}}</td>
      <td>何書いたの？ ㅎㅎ</td>
      <td>921732</td>
      <td>1</td>
      <td>2022-08-08</td>
      <td><a href="#">수정</a></td>
      <td class="text-primary">삭제</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>아</td>
      <td>호볼랄라지나</td>
      <td>5000000</td>
      <td>-3000</td>
      <td>2022-08-07</td>
      <td><a href="#">수정</a></td>
      <td class="text-primary">삭제</td>
    </tr>
    <tr>
      <th scope="row">1</th>
      <td>좀 더</td>
      <td>예쁘게 만들 방법이..</td>
      <td>42121</td>
      <td>5</td>
      <td>2022-08-06</td>
      <td><a href="#">수정</a></td>
      <td class="text-primary">삭제</td> 
    </tr>
  </tbody>
</table>
</form> -->

  <div class="admin-article d-flex flex-column align-items-center w-100">
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
        </tr>
      </thead>
      <tbody>
        <tr v-for="info in articleInfo.info" :key="info" @click="goToArticle(info.aid)">
          <td scope="row">{{ info.aid }}</td>
          <td>{{ info.atitle }}</td>
          <td>{{ info.favCount }}</td>
          <td>{{ info.saveCount }}</td>
          <td>{{ info.openCount }}</td>
          <td>{{ info.regDate }}</td>
        </tr>
      </tbody>
    </table>
    <nav aria-label="Page navigation example">
      <ul class="pagination">
        <li class="page-item"><a class="page-link" @click="getUserList(articleInfo.page-1)"
            v-if="articleInfo.page!=1">Previous</a></li>
        <li :class="articleInfo.page == page?'page-item active':'page-item'" v-for="page in articleInfo.pageList"
          :key="page"><a class="page-link" @click="getUserList(page)">{{page}}</a></li>
        <li class="page-item"><a class="page-link" @click="getUserList(articleInfo.page+1)"
            v-if="articleInfo.page!=articleInfo.totalPage">Next</a></li>
        {{array}}
      </ul>
    </nav>
  </div>
</template>
<script setup>
import { reactive } from 'vue';
import axios from 'axios';
import store from '@/store';

const headers = {
  "Content-Type": "application/json; charset=utf-8",
  Authorization: store.state.token,
  userid: store.state.userid,
};
let articleInfo = reactive({
  info: [],
  body: 3,
});

axios.post(store.state.axiosLink + "/api/setting/articlelist", { userid: store.state.userid, pageNum: articleInfo.body }, { headers }).then(function (res) {
  articleInfo.info.push(...res.data.content);
  console.log(res.data);
});

function getMorePostList() {
  axios.post(store.state.axiosLink + "/api/setting/articlelist", { userid: store.state.userid, pageNum: articleInfo.body }, { headers }).then(function (res) {
    articleInfo.info.push(...res.data.content);
  });
}

function getMorePost() {
  articleInfo.body += 1;
  getMorePostList();
}

let array = [];

function calcPage(number) {
  if (number <= 10) {
    for (let i = 1; i <= 10; i++) {
      array.push(i);
    }
    return
  } else {
    if (number % 10 == 0 && number / 10 > 0) {
      for (let i = 1; i < (((Math.round(number/10)) - 1) * 10) + 1; i++) {
        array.push(i);
      }
      return
    } else {
      if (number % 10 != 0 && number / 10 > 0) {
        for (let i = 1; i < (Math.round(number/10) * 10); i++) {
          array.push(i);
        }
        return
      }
    }
  }
}
    console.log(calcPage(15));

    window.addEventListener("scroll", windowSize);
    function windowSize() {
      if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
        getMorePost();
      }
    }

// function goToArticle(num){
//     router.push(`./read?article=${num}`)
// }
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