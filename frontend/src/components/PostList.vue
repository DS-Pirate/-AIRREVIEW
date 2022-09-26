<template>
    <div class="d-flex flex-column justify-content-center align-items-center w-100">
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
    </div>
</template>
<script setup>
    import { reactive, defineProps } from "vue";
    import store from "@/store";
    import axios from "axios";
    import router from "@/router";

    let props = defineProps(["id"])

    const headers = {
        "Content-Type": "application/json; charset=utf-8",
        Authorization: store.state.token,
        userid: store.state.userid,
    };
    let articleInfo = reactive({
        info: [],
        body: 0,
    });

    axios.post(store.state.axiosLink+"/mypage/article", { userid: props.id, pageNum: articleInfo.body }, { headers }).then(function (res) {
        articleInfo.info.push(...res.data.content);
    });

    function getMorePostList() {
        axios.post(store.state.axiosLink+"/mypage/article", { userid: props.id, pageNum: articleInfo.body }, { headers }).then(function (res) {
            articleInfo.info.push(...res.data.content);
        });
    }

    function getMorePost() {
        articleInfo.body += 1;
        getMorePostList();
    }

    window.addEventListener("scroll", windowSize);
    function windowSize() {
        if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
            getMorePost();
        }
    }
    function goToArticle(num){
        router.push(`./read?article=${num}`)
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
