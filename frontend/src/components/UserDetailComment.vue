<template>
    <div class="modal fade" :id="'UserDetail' + props.userDetail.cid" tabindex="-1" aria-labelledby="UserDetailLabel" aria-hidden="true" >
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content rounded-0">
                <div class="modal-header">
                    <span class="modal-title" id="UserDetailLabel">{{ props.userDetail.userName }}님이 해당 채널에 남긴 최근 댓글</span>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body" v-for="clist in commentList.list" :key="clist">
                    <UserDetailCommentCards :list="clist" m-0 p-0></UserDetailCommentCards>
                </div>
                <div class="modal-footer">
                    <a type="button" class="btn btn-primary" :href="'./mypage?channel='+body.userid">{{props.userDetail.userName}}님의 채널로 이동하기</a>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                </div>
            </div>
        </div>
    </div>
</template>
<script setup>
    import store from "@/store";
    import axios from "axios";
    import { defineProps, reactive } from "vue";
    import UserDetailCommentCards from "./UserDetailCommentCards.vue";
    let props = defineProps(["userDetail"]);
    let commentList = reactive({})

    const headers = {
        "Content-Type": "application/json; charset=utf-8",
        "Authorization": store.state.token,
        "userid": store.state.userid,
    };
    const body = {
        userid: props.userDetail.userid,
        authorid: store.state.authorid,
    };
    axios.post(store.state.axiosLink+"/article/userdetail", body, { headers }).then(function (res) {
        commentList.list = [...res.data]
    });
</script>
