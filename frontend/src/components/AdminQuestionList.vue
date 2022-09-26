<template>
    <div class="admin-question d-flex flex-column align-items-center w-100">
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">질문 번호</th>
                    <th scope="col">질문 제목</th>
                    <th scope="col">질문 내용</th>
                    <th scope="col">질문자</th>
                    <th scope="col">답변여부</th>
                    <th scope="col">답변하기</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="dto in questionInfo.dtoList" :key="dto" class="w-100">
                    <td>{{ dto.qid }}</td>
                    <td>{{ dto.title }}</td>
                    <td>{{ dto.context }}</td>
                    <td>{{ dto.userid }}</td>
                    <td>{{ dto.answered == false ? "미답변" : "답변" }}</td>
                    <td><button type="button" class="btn btn-primary" data-bs-toggle="modal" :data-bs-target="'#qaDetailModal'+dto.qid">답변</button></td>
                    <AdminQaModal :info="dto"></AdminQaModal>
                </tr>
            </tbody>
        </table>
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" @click="getUserList(questionInfo.page - 1)" v-if="questionInfo.page != 1">Previous</a></li>
                <li :class="questionInfo.page == page ? 'page-item active' : 'page-item'" v-for="page in questionInfo.pageList" :key="page">
                    <a class="page-link" @click="getUserList(page)">{{ page }}</a>
                </li>
                <li class="page-item"><a class="page-link" @click="getUserList(questionInfo.page + 1)" v-if="questionInfo.page != questionInfo.totalPage">Next</a></li>
            </ul>
        </nav>
    </div>
</template>
<script setup>
    import { reactive } from "vue";
    import axios from "axios";
    import store from "@/store";
import AdminQaModal from "./AdminQaModal.vue";

    let questionInfo = reactive({
        dtoList: null,
        end: null,
        next: null,
        page: null,
        pageList: null,
        prev: null,
        size: null,
        start: null,
        totalPage: null,
    });

    const headers = {
        "Content-Type": "application/json; charset=utf-8",
        Authorization: store.state.token,
        userid: store.state.userid,
    };

    function getUserList(page) {
        axios.post(store.state.axiosLinkAdmin+"/api/admin/questionmanagement", { page: page }, { headers }).then(function (res) {
            (questionInfo.dtoList = res.data.dtoList), (questionInfo.end = res.data.end), (questionInfo.next = res.data.next), (questionInfo.page = res.data.page), (questionInfo.pageList = res.data.pageList), (questionInfo.prev = res.data.prev), (questionInfo.size = res.data.size), (questionInfo.start = res.data.start), (questionInfo.totalPage = res.data.totalPag);
            console.log(res);
        });
    }
    axios.post(store.state.axiosLinkAdmin+"/api/admin/questionmanagement", { page: 1 }, { headers }).then(function (res) {
        console.log(res);
        (questionInfo.dtoList = res.data.dtoList), (questionInfo.end = res.data.end), (questionInfo.next = res.data.next), (questionInfo.page = res.data.page), (questionInfo.pageList = res.data.pageList), (questionInfo.prev = res.data.prev), (questionInfo.size = res.data.size), (questionInfo.start = res.data.start), (questionInfo.totalPage = res.data.totalPag);
    });
</script>
<style lang="sass">
    thead, tbody
        text-align: center
    tr, td
        vertical-align: middle
</style>
