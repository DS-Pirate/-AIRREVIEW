<template>
    <div class="modal fade" :id="'qaDetailModal' + info.qid" tabindex="-1" aria-labelledby="qaDetailModalLabel" aria-hidden="true" data-bs-backdrop="static">
        <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="qaDetailModalLabel">Modal title</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body d-flex flex-column gap-3">
                    <form class="form-floating">
                        <input class="form-control" type="text" id="title" :placeholder="info.title" v-model="info.title" disabled />
                        <label for="title">질문 제목</label>
                    </form>
                    <form class="form-floating">
                        <textarea style="min-height: 15rem" class="form-control" type="text" id="title" :placeholder="info.title" v-model="info.context" disabled></textarea>
                        <label for="title">질문 내용</label>
                    </form>
                    <form class="form-floating">
                        <textarea style="min-height: 15rem" class="form-control" type="text" id="title" :placeholder="info.title" v-model="info.answerContext" required></textarea>
                        <label for="title">답변 내용 입력</label>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" @click="addAnswer">Save changes</button>
                </div>
            </div>
        </div>
    </div>
</template>
<script setup>
    // import router from "@/router";
    import store from "@/store";
    import axios from "axios";
    import { defineProps, reactive } from "vue";
    let info = reactive({});
    info = props.info;
    let props = defineProps(["info"]);
    const headers = {
        "Content-Type": "application/json; charset=utf-8",
        Authorization: store.state.token,
        userid: store.state.userid,
    };

    function addAnswer() {
        axios.post(store.state.axiosLinkAdmin+"/api/admin/questionanswer", info, { headers }).then(function (res) {
            console.log(res);
            // router.go(0)
        });
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
