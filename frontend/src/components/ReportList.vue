<template>
    <div class="admin-article d-flex flex-column">
        <h1>신고 관리</h1>
        <br />
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
                <tr v-for="info in state.reportlist.dto" :key="info">
                    <td scope="row">{{ info.reid }}</td>
                    <td>{{ info.reportContext }}</td>
                    <td>{{ info.regDate.split("T")[0] }}</td>
                    <td><button @click="remove(info.reid)" class="btn btn-danger">삭제</button></td>
                </tr>
            </tbody>
        </table>
        <nav aria-label="Page">
            <ul class="pagination my-3 justify-content-center">
                <li class="page-item" v-if="state.reportlist.page != 0">
                    <span class="page-link" @click="getPostList(body.pageNum - 1)">이전</span>
                </li>
                <li :class="`page-item page-link ${state.reportlist.page == page - 1 ? 'active' : ''}`" v-for="page in state.reportlist.totalPage" @click="getPostList(page - 1)" :key="page">{{ page }}</li>
                <li class="page-item" v-if="state.next && totalPage > 10">
                    <span class="page-link" @click="getPostList(body.pageNum + 1)">다음</span>
                </li>
            </ul>
        </nav>
    </div>
</template>
<script setup>
    import { reactive } from "vue";
    import axios from "axios";
    import store from "@/store";
    import router from "@/router";

    let state = reactive({
        reportlist: "",
        pageNum: 0,
    });

    const url = store.state.axiosLink + "/api/setting/reportlist";
    const headers = {
        "Content-Type": "application/json",
        Authorization: store.state.token,
        userid: store.state.userid,
    };

    let body = {
        pageNum: 0,
        userid: store.state.userid,
    };
    function getPostList(reqNum) {
        reqNum > 0 || reqNum != null ? (body.pageNum = reqNum) : 0;
        state.reportlist = "";
        axios.post(url, body, { headers }).then(function (res) {
            state.reportlist = JSON.parse(JSON.stringify(res.data));
            body.pageNum = reqNum;
        });
    }
    getPostList(state.pageNum);
    function remove(num) {
        axios.post(store.state.axiosLink + "/api/setting/reportremove", num, { headers }).then(function (res) {
            res.data == true ? alert("삭제되었습니다") : alert("실패하였습니다");
            router.go(0);
        });
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
