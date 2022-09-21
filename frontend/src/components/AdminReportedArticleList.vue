<template>
    <div class="admin-report d-flex flex-column align-items-center w-100">
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">글 번호</th>
                    <th scope="col">글쓴이</th>
                    <th scope="col">조회수</th>
                    <th scope="col">삭제</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="dto in reportInfo.dtoList" :key ="dto" class="w-100">
                    <td>{{dto.articleid}}</td>
                    <td>{{dto.userid}}</td>
                    <td>{{dto.reportContext}}</td>
                    <td><button class="btn btn-danger">삭제</button></td>
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

    const headers = {
        "Content-Type": "application/json; charset=utf-8",
        "Authorization": store.state.token,
        "userid": store.state.userid,
    };

    function getUserList(page){
        axios.post("../api/admin/reportmanagement", { page:page }, { headers })
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
    axios.post("../api/admin/reportmanagement", { page:1 }, { headers })
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
</style>
