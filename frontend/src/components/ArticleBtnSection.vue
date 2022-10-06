<template>
    <ul class="actions mx-0 px-1">
        <li>
            <button class="border-0 bg-white" data-bs-toggle="modal" data-bs-target="#share"><span>Share</span></button>
        </li>
        <li v-if="functionBtnChecking()">
            <button class="border-0 bg-white" @click="addToLike"><span ref="fav">Like</span></button>
        </li>
        <li v-if="functionBtnChecking()">
            <button class="border-0 bg-white" @click="addSave"><span ref="save">Save</span></button>
        </li>
        <li v-if="functionBtnChecking()">
            <button class="border-0 bg-white" data-bs-toggle="modal" data-bs-target="#ReportModal"><span ref="report">Report</span></button>
        </li>
        <li v-if="stat.result">
            <button type="button" v-on:click="reask" class="border-0 bg-white"><span class="text-primary">Modify</span></button>
        </li>
    </ul>
    <ShareModal></ShareModal>
</template>
<script setup>
    import axios from "axios";
    import { ref, defineProps, reactive } from "vue";
    import { useRouter } from "vue-router";
    import { useStore } from 'vuex'
    import ShareModal from "./ShareModal.vue";
    const store = useStore()
    const router = useRouter();
    let props = defineProps(["id"]);
    let fav = ref(null);
    let report = ref(null);
    let save = ref(null);
    let stat = reactive({
        result : null
    })
    function functionBtnChecking() {
        if (store.state.userid != 0) {
            return true;
        } else {
            return false;
        }
    }
    function modifiable() {
        const headers = {
            "Content-Type": "application/json; charset=utf-8",
            "Authorization": store.state.token,
            "userid" : store.state.userid
        }
        let body = {
            aid: new URLSearchParams(window.location.search).get("article"),
            userid: store.state.userid,
        }
        axios
        .post(store.state.axiosLink+"/api/article/modify/check", body, { headers })
        .then(function (res) {
            if (res.data.aid==props.id) {
                stat.result = true
            }else{
                stat.result = false
            }
        })
    }

    async function addToLike() {
        const url = store.state.axiosLink+"/api/article/like";
        const headers = {
            "Content-Type": "application/json; charset=utf-8",
            "Authorization": store.state.token,
            "userid" : store.state.userid
        };
        let body = {
            userid: store.state.userid,
            aid: props.id,
        };
        await axios
            .post(url, body, { headers })
            .then((res) => alert(res.data))
            .catch((e) => console.log(e));
        router.go(0);
    }
    async function addSave() {
        const url = store.state.axiosLink+"/api/article/save";
        const headers = {
            "Content-Type": "application/json; charset=utf-8",
            "Authorization": store.state.token,
            "userid" : store.state.userid
        };
        let body = {
            userid: store.state.userid,
            aid: props.id,
        };
        await axios
            .post(url, body, { headers })
            .then((res) => alert(res.data))
            .catch((e) => console.log(e));
        router.go(0);
    }

    function getArticleFunctionStatus() {
        const url = store.state.axiosLink+"/api/article/functions";
        const headers = {
            "Content-Type": "application/json; charset=utf-8",
            "Authorization": store.state.token,
            "userid" : store.state.userid
        };
        let body = {
            userid: store.state.userid,
            aid: props.id,
        };

        axios
            .post(url, body, { headers })
            .then(function (res) {
                if (res.data.favo == true) {
                    fav.value.style.color = "#3041a9";
                }
                if (res.data.save == true) {
                    save.value.style.color = "#3041a9";
                }
                if (res.data.report == true) {
                    report.value.style.color = "red";
                    report.value.textContent = "Reported";
                }
            })
            .catch((e) => {
                console.log(e);
            });
    }
    function reask(){
        let result = confirm("리뷰 정확도를 위해 수정할 경우 모든 평가(댓글)가 사라집니다 수정하시겠습니까 ?")
        if(result){
            router.push(`/modify?article=` + props.id)
        }
    }
    getArticleFunctionStatus();
    modifiable()
</script>
