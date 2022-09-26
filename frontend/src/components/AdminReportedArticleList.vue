<template>
    <div class="admin-report d-flex flex-column align-items-center w-100">
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">글 번호</th>
                    <th scope="col">글쓴이</th>
                    <th scope="col">신고 내용</th>
                    <th scope="col">글 보기</th>
                    <th scope="col">신고 취소</th>
                    <th scope="col">글 삭제</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="dto in reportInfo.dtoList" :key ="dto" class="w-100">
                    <td>{{dto.articleid}}</td>
                    <td>{{dto.userid}}</td>
                    <td>{{dto.reportContext}}</td>
                    <td><a :href='"../read?article="+dto.articleid'><button class="btn btn-primary">글로 이동</button></a></td>
                    
                    <td><button class="btn btn-warning" @click="reportCancel(dto.reid)">신고 취소</button></td>
                    <td><button class="btn btn-danger" @click=DeleteArticle(dto.articleid) >글 삭제</button></td>
                </tr>
            </tbody>
        </table>
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" @click="getUserList(reportInfo.page-1)" v-if="reportInfo.page!=1">Previous</a></li>
                <li :class="reportInfo.page == page?'page-item active':'page-item'" v-for="page in reportInfo.pageList"  :key="page"><a class="page-link" @click="getUserList(page)">{{page}}</a></li>
                <li class="page-item" ><a class="page-link" @click="getUserList(reportInfo.page+1)" v-if="reportInfo.page!=reportInfo.totalPage">Next</a></li>
            </ul>
        </nav>
    </div>
</template>
<script setup>
    import { reactive  } from 'vue';
    import axios from 'axios';
    import store from '@/store';
    import router from '@/router';
    const headers = {
        "Content-Type": "application/json; charset=utf-8",
        "Authorization": store.state.token,
        "userid": store.state.userid,
    };

    function DeleteArticle(num) {
        let body = {aid:num}
        axios.post(store.state.axiosLinkAdmin+"/api/admin/articlemanagement/delete", body, { headers }).then(function (res) {res.data == true?router.go(0):alert("삭제실패")});
    }
    function reportCancel(num) {
        let body = {reid:num}
        axios.post(store.state.axiosLinkAdmin+"/api/admin/reportmanagement/delete", body, { headers }).then(function (res) {res.data == true?router.go(0):alert("삭제실패")});
    }

    let reportInfo = reactive({
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



    function getUserList(page){
        axios.post(store.state.axiosLinkAdmin+"/api/admin/reportmanagement", { page:page }, { headers })
    .then(function(res){
        reportInfo.dtoList =  res.data.dtoList,
        reportInfo.end =  res.data.end,
        reportInfo.next =  res.data.next,
        reportInfo.page =  res.data.page,
        reportInfo.pageList =  res.data.pageList,
        reportInfo.prev =  res.data.prev,
        reportInfo.size =  res.data.size,
        reportInfo.start =  res.data.start,
        reportInfo.totalPage = res.data.totalPag
        console.log(res);
    })  
    }
    axios.post(store.state.axiosLinkAdmin+"/api/admin/reportmanagement", { page:1 }, { headers })
    .then(function(res){
        console.log(res);
        reportInfo.dtoList =  res.data.dtoList,
        reportInfo.end =  res.data.end,
        reportInfo.next =  res.data.next,
        reportInfo.page =  res.data.page,
        reportInfo.pageList =  res.data.pageList,
        reportInfo.prev =  res.data.prev,
        reportInfo.size =  res.data.size,
        reportInfo.start =  res.data.start,
        reportInfo.totalPage = res.data.totalPag
    })
</script>
<style lang="sass">
    thead, tbody
        text-align: center
    tr, td
        vertical-align: middle
</style>
