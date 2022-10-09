<template>
    <div class="admin-user d-flex flex-column align-items-center w-100">
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">회원번호</th>
                    <th scope="col">이메일</th>
                    <th scope="col">이름</th>
                    <th scope="col">성별</th>
                    <th scope="col">유저 소개</th>
                    <th scope="col">관리</th>
                </tr>
            </thead>
            <tbody class="w-100">
                <tr v-for="dto in userInfo.dtoList" :key="dto" class="w-100">
                    <td>{{ dto.userid }}</td>
                    <td>{{ dto.email }}</td>
                    <td>{{ dto.airName }}</td>
                    <td>{{ dto.gender == 1 ? "남" : "여" }}</td>
                    <td>{{ dto.userIntro }}</td>
                    <td><button type="button" class="btn btn-primary" data-bs-toggle="modal" :data-bs-target="`#userDetailModal${dto.userid}`">관리</button></td>
                    <AdminUserDetailModal :info="dto"></AdminUserDetailModal>
                </tr>
            </tbody>
        </table>
        <nav aria-label="Page">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" @click="getUserList(userInfo.page - 1)" v-if="userInfo.page != 1">Previous</a></li>
                <li :class="userInfo.page == page ? 'page-item active' : 'page-item'" v-for="page in userInfo.pageList" :key="page">
                    <a class="page-link" @click="getUserList(page)">{{ page }}</a>
                </li>
                <li class="page-item"><a class="page-link" @click="getUserList(userInfo.page + 1)" v-if="userInfo.page != userInfo.totalPage">Next</a></li>
            </ul>
        </nav>
        <div class="searching-area d-flex gap-2">
            <select class="form-select w-50" aria-label="useroptions" v-model="serchingInfo.type">
                <option value="name">이름</option>
                <option value="mail">이메일</option>
            </select>
            <input class="form-control" type="text" v-model="serchingInfo.keyword"/>
            <button class="btn btn-primary" style="white-space:nowrap"  @click="getSearchingList">검색</button>
        </div>
    </div>
</template>
<script setup>
    import { reactive } from "vue";
    import axios from "axios";
    import store from "@/store";
    import AdminUserDetailModal from "./AdminUserDetailModal.vue";


    // let page = new URLSearchParams(window.location.search).get("page")
    // let type = new URLSearchParams(window.location.search).get("type")


    let userInfo = reactive({
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
    let serchingInfo = reactive({
        keyword:"",
        type:"name"
    })

    function getUserList(page) {
        axios.post(store.state.axiosLinkAdmin+"/api/admin/usermanagement", { page: page, keyword: serchingInfo.keyword, type: serchingInfo.type }, { headers }).then(function (res) {
            (userInfo.dtoList = res.data.dtoList), (userInfo.end = res.data.end), (userInfo.next = res.data.next), (userInfo.page = res.data.page), (userInfo.pageList = res.data.pageList), (userInfo.prev = res.data.prev), (userInfo.size = res.data.size), (userInfo.start = res.data.start), (userInfo.totalPage = res.data.totalPag);
        });
    }
    axios.post(store.state.axiosLinkAdmin+"/api/admin/usermanagement", { page: 1 }, { headers }).then(function (res) {
        (userInfo.dtoList = res.data.dtoList), (userInfo.end = res.data.end), (userInfo.next = res.data.next), (userInfo.page = res.data.page), (userInfo.pageList = res.data.pageList), (userInfo.prev = res.data.prev), (userInfo.size = res.data.size), (userInfo.start = res.data.start), (userInfo.totalPage = res.data.totalPag);
    });
    
    function getSearchingList() {
        axios.post(store.state.axiosLinkAdmin+"/api/admin/usermanagement", { page: 1, keyword: serchingInfo.keyword, type: serchingInfo.type }, { headers }).then(function (res) {
            (userInfo.dtoList = res.data.dtoList), (userInfo.end = res.data.end), (userInfo.next = res.data.next), (userInfo.page = res.data.page), (userInfo.pageList = res.data.pageList), (userInfo.prev = res.data.prev), (userInfo.size = res.data.size), (userInfo.start = res.data.start), (userInfo.totalPage = res.data.totalPag);

        });
    }

</script>
<style lang="sass">
    thead, tbody
        text-align: center
    tr, td
        vertical-align: middle
</style>
