<template>
  <form class="d-flex flex-column w-100" action="postmanagement" method="post">
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
</form>

  <!-- <div class="admin-article d-flex flex-column align-items-center w-100">
    <table class="table">
      <thead>
        <tr>
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
      <tbody class="w-100">
        <tr v-for="dto in ArticleInfo.dtoList" :key="dto" class="w-100">
          <td>{{dto.aid}}</td>
          <td>{{dto.title}}</td>
          <td>{{dto.context}}</td>
          <td>{{dto.like}}</td>
          <td>{{dto.unlike}}</td>
          <td>{{dto.regdate}}</td>
          <td>{{dto.modify}}</td>
          <td>{{dto.remove}}</td>
          <td><button type="button" class="btn btn-primary" data-bs-toggle="modal"
              :data-bs-target="`#userDetailModal${dto.aid}`">관리</button></td>
          <AdminArticleDetailModalVue :info="dto"></AdminArticleDetailModalVue>
        </tr>
      </tbody>
    </table>
    <nav aria-label="Page navigation example">
      <ul class="pagination">
        <li class="page-item"><a class="page-link" @click="getUserList(ArticleInfo.page-1)"
            v-if="ArticleInfo.page!=1">Previous</a></li>
        <li :class="ArticleInfo.page == page?'page-item active':'page-item'" v-for="page in ArticleInfo.pageList"
          :key="page"><a class="page-link" @click="getUserList(page)">{{page}}</a></li>
        <li class="page-item"><a class="page-link" @click="getUserList(ArticleInfo.page+1)"
            v-if="ArticleInfo.page!=ArticleInfo.totalPage">Next</a></li>
      </ul>
    </nav>
    <div class="searching-area d-flex gap-2">
      <select class="form-select w-50" aria-label="useroptions" v-model="serchingInfo.type">
        <option value="title">제목</option>
        <option value="context">내용</option>
      </select>
      <input class="form-control" type="text" v-model="serchingInfo.keyword" />
      <button class="btn btn-primary" style="white-space:nowrap" @click="getSearchingList">검색</button>
    </div>
  </div>  -->
</template>
<script>
import { reactive } from 'vue';
import axios from 'axios';
// import store from '@/store';
import { useStore } from "vuex";

// import AdminArticleDetailModalVue from './AdminArticleDetailModal.vue.vue';

export default {
  name: "MyAccount",
  setup() {
    const state = reactive({
      userid: '',
      aid: '',
      atilte: '',
      context: '',
      regdate: '',
    })
  
    const store = useStore();
    const url = './api/setting/articlelist'
    const headers = {
      "Content-Type": "application/json",
      "Authorization": store.state.token,
      "userid": store.state.userid
    }

    let body = {
      userid: store.state.userid,
    }

    axios.post(url, body, { headers }).then(function (res) {
      state.userid = res.data.userId;
      state.aid = res.data.aid;
      state.title = res.data.atitle;
      state.context = res.data.context;
      state.regdate = res.data.regdate;

      const regdate = new Date(Date.parse(res.data.regdate))
      state.year = regdate.getFullYear();
      state.date = regdate.getDate();
      state.month = regdate.getMonth() + 1;
      console.log(res.data);
    })
   return {state}
  }
}



// let ArticleInfo = reactive({
//   dtoList: null,
//   end: null,
//   next: null,
//   page: null,
//   pageList: null,
//   prev: null,
//   size: null,
//   start: null,
//   totalPage: null
// })

// let serchingInfo = reactive({
//   keyword: "",
//   type: "title"
// })

// const headers = {
//   "Content-Type": "application/json; charset=utf-8",
//   "Authorization": store.state.token,
//   "userid": store.state.userid,
// };


// function getUserList(page) {
//   axios.post("../api/setting/articlelist", { page: page, keyword: serchingInfo.keyword, type: serchingInfo.type }, { headers })
//     .then(function (res) {
//       ArticleInfo.dtoList = res.data.dtoList,
//         ArticleInfo.end = res.data.end,
//         ArticleInfo.next = res.data.next,
//         ArticleInfo.page = res.data.page,
//         ArticleInfo.pageList = res.data.pageList,
//         ArticleInfo.prev = res.data.prev,
//         ArticleInfo.size = res.data.size,
//         ArticleInfo.start = res.data.start,
//         ArticleInfo.totalPage = res.data.totalPag
//       console.log(res);
//     })
// }

// axios.post("../api/setting/articlelist", { page: 1 }, { headers })
//   .then(function (res) {
//     ArticleInfo.dtoList = res.data.dtoList,
//       ArticleInfo.end = res.data.end,
//       ArticleInfo.next = res.data.next,
//       ArticleInfo.page = res.data.page,
//       ArticleInfo.pageList = res.data.pageList,
//       ArticleInfo.prev = res.data.prev,
//       ArticleInfo.size = res.data.size,
//       ArticleInfo.start = res.data.start,
//       ArticleInfo.totalPage = res.data.totalPag
//   })

// function getSearchingList() {
//   axios.post("../api/setting/articlelist", { page: 1, keyword: serchingInfo.keyword, type: serchingInfo.type }, { headers }).then(function (res) {
//     (ArticleInfo.dtoList = res.data.dtoList), (ArticleInfo.end = res.data.end), (ArticleInfo.next = res.data.next), (ArticleInfo.page = res.data.page), (ArticleInfo.pageList = res.data.pageList), (ArticleInfo.prev = res.data.prev), (ArticleInfo.size = res.data.size), (ArticleInfo.start = res.data.start), (ArticleInfo.totalPage = res.data.totalPag);
//     console.log(res);
//   });
// }

</script>
<style lang="sass">
  thead, tbody
      text-align: center
  tr, td
      vertical-align: middle
</style>