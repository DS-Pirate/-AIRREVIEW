<template>
    <div class="admin-article d-flex flex-column align-items-center w-100">
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">글 번호</th>
                    <th scope="col">글 제목</th>
                    <th scope="col">조회수</th>
                    <th scope="col">관리</th>
                </tr>
            </thead>
            <tbody class="w-100">
                <tr v-for="dto in ArticleInfo.dtoList" :key ="dto" class="w-100">
                    <td>{{dto.aid}}</td>
                    <td>{{dto.atitle}}</td>
                    <td>{{dto.opencount}}</td>
                    <td><button type="button" class="btn btn-primary" data-bs-toggle="modal" :data-bs-target="`#userDetailModal${dto.aid}`">관리</button></td>
                    <AdminArticleDetailModalVue :info="dto"></AdminArticleDetailModalVue>
                </tr>
            </tbody>
        </table>
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" @click="getUserList(ArticleInfo.page-1)" v-if="ArticleInfo.page!=1">Previous</a></li>
                <li :class="ArticleInfo.page == page?'page-item active':'page-item'" v-for="page in ArticleInfo.pageList"  :key="page"><a class="page-link" @click="getUserList(page)">{{page}}</a></li>
                <li class="page-item" ><a class="page-link" @click="getUserList(ArticleInfo.page+1)" v-if="ArticleInfo.page!=ArticleInfo.totalPage">Next</a></li>
            </ul>
        </nav>
        <div class="searching-area d-flex gap-2">
            <select class="form-select w-50" aria-label="useroptions" v-model="serchingInfo.type">
                <option value="title">제목</option>
                <option value="context">내용</option>
            </select>
            <input class="form-control" type="text" v-model="serchingInfo.keyword"/>
            <button class="btn btn-primary" style="white-space:nowrap"  @click="getSearchingList">검색</button>
        </div>
    </div>
</template>
<script setup>
    import { reactive  } from 'vue';
    import axios from 'axios';
    import store from '@/store';
    import AdminArticleDetailModalVue from './AdminArticleDetailModal.vue.vue';

    let ArticleInfo = reactive({
        dtoList : null,
        end : null,
        next : null,
        page : null,
        pageList : null,
        prev : null,
        size : null,
        start : null,
        totalPage : null
    })

    const headers = {
        "Content-Type": "application/json; charset=utf-8",
        "Authorization": store.state.token,
        "userid": store.state.userid,
    };


    function getUserList(page){
        axios.post("../api/admin/articlemanagement", { page:page }, { headers })
    .then(function(res){
        ArticleInfo.dtoList =  res.data.dtoList,
        ArticleInfo.end =  res.data.end,
        ArticleInfo.next =  res.data.next,
        ArticleInfo.page =  res.data.page,
        ArticleInfo.pageList =  res.data.pageList,
        ArticleInfo.prev =  res.data.prev,
        ArticleInfo.size =  res.data.size,
        ArticleInfo.start =  res.data.start,
        ArticleInfo.totalPage = res.data.totalPag
        console.log(res);
    })  
    }
    axios.post("../api/admin/articlemanagement", { page:1 }, { headers })
    .then(function(res){
        ArticleInfo.dtoList =  res.data.dtoList,
        ArticleInfo.end =  res.data.end,
        ArticleInfo.next =  res.data.next,
        ArticleInfo.page =  res.data.page,
        ArticleInfo.pageList =  res.data.pageList,
        ArticleInfo.prev =  res.data.prev,
        ArticleInfo.size =  res.data.size,
        ArticleInfo.start =  res.data.start,
        ArticleInfo.totalPage = res.data.totalPag
    })
    let serchingInfo = reactive({
        keyword:null,
        type:"name"
    })
    function getSearchingList() {
        axios.post("../api/admin/articlemanagement", { page: 1, keyword: serchingInfo.keyword, type: serchingInfo.type }, { headers }).then(function (res) {
            (ArticleInfo.dtoList = res.data.dtoList), (ArticleInfo.end = res.data.end), (ArticleInfo.next = res.data.next), (ArticleInfo.page = res.data.page), (ArticleInfo.pageList = res.data.pageList), (ArticleInfo.prev = res.data.prev), (ArticleInfo.size = res.data.size), (ArticleInfo.start = res.data.start), (ArticleInfo.totalPage = res.data.totalPag);
            console.log(res);
        });
    }
</script>
<style lang="sass">
    thead, tbody
        text-align: center
    tr, td
        vertical-align: middle
</style>
