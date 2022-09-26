<template>
    <div class="offcanvas offcanvas-start" data-bs-scroll="true" tabindex="-1" id="subscribe"
        aria-labelledby="subscribe">
        <div class="offcanvas-header">
            <h5 class="offcanvas-title" id="subscribeTitle">Subscribe List</h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div class="offcanvas-body">
          <SubscribeList v-for="info in subs.Info " :key="info" :subsinfo="info" ></SubscribeList>  
        </div>
    </div>
</template>
<script setup>
import { reactive } from "vue"
import store from '@/store';
import axios from 'axios';
import SubscribeList from './SubscribeList.vue';

const headers = {
        "Authorization": store.state.token,
        "userid" : store.state.userid
    }

    let body = {
        userid: store.state.userid
    }

    let subs = reactive({
        Info: {}
    })

axios.post(`${store.state.axiosLink}/api/subs`, body, {headers} ).then(function(res) {
    subs.Info = res.data;
})
</script>
<style lang="">
    
</style>