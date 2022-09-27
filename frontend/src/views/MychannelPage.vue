<template>
    <div class="channelpage">
        <div class="chimg p-3 m-0 shadow-sm bg-white">
            <img :src="store.state.axiosLink+'/images/read/ch/' + id" class="ch_img w-100" alt="" />
            <input type="file" v-on:change="fileUpload($event)" class="w-100 h-100 fileinput form-control" id="formFile" v-if="store.state.userid==id"/>
        </div>
        <div class="chcategory p-1 my-3 shadow-sm bg-white d-flex justify-content-center">
            <ul class="list-group list-group-horizontal h-10 align-items-center">
                <li class="list-group-item border border-0" v-on:click="change('list')">글 목록</li>
                <span>|</span>
                <li class="list-group-item border border-0" v-on:click="change('category')">구독 정보</li>
                <span>|</span>
                <li class="list-group-item border border-0" v-on:click="change('channelinfo')">내 정보</li>
            </ul>
        </div>
        <div class="chcategory p-3 my-3 shadow-sm bg-white d-flex h-auto" v-if="state.form == 'list'">
            <PostList :id="id" />
        </div>
        <div class="chcategory p-3 my-3 shadow-sm bg-white d-flex h-auto" v-else-if="state.form == 'category'">
            <SubInfo :id="id" />
        </div>
        <div class="chcategory p-3 my-3 shadow-sm bg-white d-flex h-auto" v-else-if="state.form == 'channelinfo'">
            <MyInfo />
        </div>
    </div>
</template>
<script setup>
    import { reactive } from "vue";
    import SubInfo from "@/components/SubscriptionInfo.vue";
    import PostList from "@/components/PostList.vue";
    import MyInfo from "@/components/MyInfo.vue";
    import axios from "axios";
    import store from "@/store";

    let state = reactive({
        form: "list",
    });

    let id = new URLSearchParams(window.location.search).get("channel");

    function change(which) {
        state.form = which;
        console.log(state.form);
    }

    let headers = { token: store.state.token };

    function fileUpload(e) {
        let formData = new FormData();
        formData.append("upload", e.target.files[0]);
        axios.post(`${store.state.axiosLink}/mypage/image/upload/${store.state.userid}`, formData, { headers }).then(function (res) {
            console.log(res);
        });
    }

    console.log("현재채널" + id);
</script>
<style lang="sass" scoped>
    .chimg
        position: relative
    .fileinput
        position: absolute
        opacity: 0
        top:0
        left: 0
        transition: 0.2s all ease-in-out
        &:hover
            opacity: 1
</style>
