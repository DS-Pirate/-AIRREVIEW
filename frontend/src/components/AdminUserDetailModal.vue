<template>
    <div class="modal fade" :id="'userDetailModal' + info.userid" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" :aria-labelledby="'userDetailModal' + info.userid + 'Label'" aria-hidden="true">
        <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
            <div class="modal-content">
                <div class="modal-header">
                    <h5>{{ info.airName }}번 글 정보</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body d-flex flex-column gap-3">
                    <form class="form-floating">
                        <input class="form-control" type="text" id="useremail" :placeholder="info.email" v-model="info.email" required />
                        <label for="userid">이메일</label>
                    </form>
                    <form class="form-floating">
                        <input class="form-control" type="text" id="username" :placeholder="info.airName" v-model="info.airName" required />
                        <label for="username">이름</label>
                    </form>
                    <form class="form-floating">
                        <input class="form-control" type="text" id="gender" :placeholder="info.gender" v-model="info.gender" required />
                        <label for="gender">성별</label>
                    </form>
                    <form class="form-floating">
                        <input class="form-control" type="text" id="regdate" :placeholder="info.birthDay" v-model="info.birthDay" required />
                        <label for="regdate">가입일</label>
                    </form>
                    <form class="form-floating">
                        <input class="form-control" type="text" id="userintro" :placeholder="info.userIntro" v-model="info.userIntro" required />
                        <label for="userintro">자기소개</label>
                    </form>
                    <form class="form-floating">
                        <input class="form-control" type="text" id="userauth" :placeholder="info.auth" v-model="info.auth" required />
                        <label for="userauth">유저권한</label>
                    </form>
                    <form class="form-floating">
                        <input class="form-control" type="text" id="chname" :placeholder="info.chName" v-model="info.chName" required />
                        <label for="chname">채널이름</label>
                    </form>
                    <form class="form-floating">
                        <input class="form-control" type="text" id="question1" :placeholder="info.q1" v-model="info.q1" required />
                        <label for="question1">질문1</label>
                    </form>
                    <form class="form-floating">
                        <input class="form-control" type="text" id="quetion2" :placeholder="info.q2" v-model="info.q2" required />
                        <label for="quetion2">질문2</label>
                    </form>
                    <form class="form-floating">
                        <input class="form-control" type="text" id="quetion3" :placeholder="info.q3" v-model="info.q3" required />
                        <label for="quetion3">질문3</label>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                    <button type="button" class="btn btn-danger" @click="DeleteUser">삭제하기</button>
                    <button type="button" class="btn btn-primary" @click="modifyUser">수정하기</button>
                </div>
            </div>
        </div>
    </div>
</template>
<script setup>
    import router from "@/router";
    import store from "@/store";
    import axios from "axios";
    import { defineProps, reactive } from "vue";

    let info = reactive({});
    info = props.info;
    let props = defineProps(["info"]);
    const headers = {
        "Content-Type": "application/json; charset=utf-8",
        "Authorization": store.state.token,
        "userid": store.state.userid,
    };
    function modifyUser() {
        axios.post("../api/admin/usermanagement/modify", info, { headers }).then(function (res) {res.data == true?router.go(0):alert("업데이트실패")});
    }
    function DeleteUser() {
        axios.post("../api/admin/usermanagement/delete", info, { headers }).then(function (res) {res.data == true?router.go(0):alert("업데이트실패")});
    }
</script>
<style lang="sass">
    label
        text-align: left
    .modal-body
        margin-left: 0.2rem
        &::-webkit-scrollbar
            width: 0.2rem
            background-color: lightgray
        &::-webkit-scrollbar-thumb
            background-color: black
</style>
