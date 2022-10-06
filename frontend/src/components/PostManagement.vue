<template>
    <div class="admin-article d-flex flex-column w-100 ">
        <h1>글 관리</h1>
        <br />
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
                <tr v-for="info in state.info.content" :key="info">
                    
                    <td @click="goToArticle(info.aid)" scope="row">{{ info.aid }}</td>
                    <td @click="goToArticle(info.aid)">{{ info.atitle }}</td>
                    <td @click="goToArticle(info.aid)">{{ info.favCount }}</td>
                    <td @click="goToArticle(info.aid)">{{ info.saveCount }}</td>
                    <td @click="goToArticle(info.aid)">{{ info.openCount }}</td>
                    <td @click="goToArticle(info.aid)">{{ info.regDate }}</td>
                    <td><button class="btn btn-warning" @click="reask(info.aid)">수정</button></td>
                    <td><button class="btn btn-danger" @click="remove(info.aid)">삭제</button></td>
                </tr>
            </tbody>
        </table>
        <nav aria-label="Page">
            <ul class="pagination justify-content-center">
                <li class="page-item" v-if="state.info.number != 0">
                    <span class="page-link" @click="getPage(body.pageNum-1)">이전</span>
                </li>
                <li :class="`page-item page-link ${state.info.number==page-1?'active':''}`" v-for="page in state.pageNumList" @click="getPage(page-1)" :key="page">{{ page }}</li>
                <li class="page-item" v-if="state.next  && state.pageNumList>10">
                    <span class="page-link" @click="getPage(body.pageNum+1)">다음</span>
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
        info: [],
        next: false,
        pageNumList : null

    });

    const url = store.state.axiosLink + "/api/setting/articlelist/";
    const headers = {
        "Content-Type": "application/json",
        Authorization: store.state.token,
        userid: store.state.userid,
    };

    let body = {
        userid: store.state.userid,
        pageNum: 0,
    };
    function getPage(reqNum) {
        reqNum>0||reqNum!=null? body.pageNum=reqNum : 0
        state.info = []
        axios.post(url, body, { headers }).then(function (res) {
            state.info = JSON.parse(JSON.stringify(res.data))
            state.pageNumList = Math.ceil(res.data.totalElements / 10);
            body.pageNum = reqNum
            if (res.data.number * 10 + res.data.numberOfElements == res.data.totalElements) {
                state.next = false;
            } else {
                state.next = true;
            }
        });
    }
    function remove(num) {
        axios.post(store.state.axiosLink + "/api/setting/articleremove", num, { headers }).then(function (res) {
            res.data == true ? alert("삭제되었습니다") : alert("실패하였습니다");
            router.go(0);
        });
    }
    function goToArticle(num) {
        router.push(`./read?article=${num}`);
    }

    function reask(postid){
        let result = confirm("리뷰 정확도를 위해 수정할 경우 모든 평가(댓글)가 사라집니다 수정하시겠습니까 ?")
        if(result){
            router.push(`/modify?article=` + postid)
        }else{
        return
    }

    }
    getPage(0)
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
