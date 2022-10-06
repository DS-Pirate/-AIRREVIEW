<template>
    <div class="d-flex flex-column">
        <h1>Q&A</h1>
        <br />
        <div class="accordion accordion-flush w-100" id="accordionFlushExample">
            <div v-for="(info, idx) in state.questionlist.dto" :key="(info, idx)" class="accordion-item">
                <h2 class="accordion-header" id="flush-headingOne">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" :data-bs-target="'#flush-collapseOne' + idx" aria-expanded="true" aria-controls="flush-collapseOne">
                        <h5>{{ info.title }}</h5>
                    </button>
                </h2>
                <div :id="'flush-collapseOne' + idx" class="accordion-collapse collapse px-4" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                    <div class="accordion-body">
                        {{ info.context }}
                        <hr />
                        <div>
                            <p>{{ info.answerContext }}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <nav aria-label="Page">
            <ul class="pagination justify-content-center">
                <li class="page-item" v-if="state.questionlist.page != 0">
                    <span class="page-link" @click="getQNAList(body.pageNum-1)">이전</span>
                </li>
                <li :class="`page-item page-link ${state.questionlist.page == page - 1 ? 'active' : ''}`" v-for="page in state.questionlist.totalPage" @click="getQNAList(page - 1)" :key="page">{{ page }}</li>
                <li class="page-item" v-if="state.next && totalPage > 10">
                    <span class="page-link" @click="getQNAList(body.pageNum+1)">다음</span>
                </li>
            </ul>
        </nav>
    </div>
</template>
<script setup>
    import { reactive } from "vue";
    import axios from "axios";
    import store from "@/store";

    const state = reactive({
        questionlist: "",
        pageNum: 0,
    });

    const url = store.state.axiosLink + "/api/setting/questionlist";
    const headers = {
        "Content-Type": "application/json",
        Authorization: store.state.token,
        userid: store.state.userid,
    };

    let body = {
        userid: store.state.userid,
        pageNum: 0,
    };

    function getQNAList(reqNum) {
        reqNum > 0 || reqNum != null ? (body.pageNum = reqNum) : 0;
        state.questionlist = "";
        axios.post(url, body, { headers }).then(function (res) {
            state.questionlist = JSON.parse(JSON.stringify(res.data));
            console.log(res);
        });
        body.pageNum = reqNum
    }
    getQNAList(state.pageNum);
</script>
<style scoped lang="sass">
    .accordion-collapse
      background-color:gray
</style>
