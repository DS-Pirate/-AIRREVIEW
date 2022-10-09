<template>
    <div class="modal fade" :id="'userDetailModal' + info.aid" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" :aria-labelledby="'userDetailModal' + info.aid + 'Label'" aria-hidden="true">
        <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
            <div class="modal-content">
                <div class="modal-header">
                    <h5>{{ info.aid }}번 글의 정보</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body d-flex flex-column gap-3">
                    <form class="form-floating">
                        <input class="form-control" type="text" id="atitle" :placeholder="info.atitle" v-model="info.atitle" required />
                        <label for="atitle">제목</label>
                    </form>
                    <form style="text-align:left;" class=" border border-1 p-2">
                        <label for="context">내용</label>
                        <span class="rounded-0 p-0" style="text-align:left;" type="text" id="context"  v-html="info.context"  ></span>
                    </form>
                    <form class="form-floating">
                        <input class="form-control" type="text" id="tags" :placeholder="info.tags" v-model="info.tags" required />
                        <label for="tags">태그</label>
                    </form>
                    <form class="form-floating">
                        <input class="form-control" type="text" id="opened" :placeholder="info.opened" v-model="info.opened" required />
                        <label for="opened">공개여부</label>
                    </form>
                    <form class="form-floating">
                        <input class="form-control" type="text" id="regdate" :placeholder="info.regdate" v-model="info.regdate" required />
                        <label for="regdate">등록일</label>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                    <button type="button" class="btn btn-danger" @click="DeleteArticle">삭제하기</button>
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
    let body = {
        qid : props.info.qid
    }
    const headers = {
        "Content-Type": "application/json; charset=utf-8",
        "Authorization": store.state.token,
        "userid": store.state.userid,
    };
    axios.post(store.state.axiosLinkAdmin+"/api/admin/articlemanagement/hashs", body, { headers }).then(function (res) {
        info.tags = res.data.length!=0?res.data[0].split(" "):""
    })
    function DeleteArticle() {
        let body = {aid:info.aid}
        axios.post(store.state.axiosLinkAdmin+"/api/admin/articlemanagement/delete", body, { headers }).then(function (res) {res.data == true?router.go(0):alert("삭제실패")});
    }

    
</script>
<style scoped lang="sass">
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
