<template>
    <div class="mainviewpage">
        <div class="btn-group" role="group" aria-label="Default button group">
            <h2>최근에 조회한 글</h2>
        </div>
        <div class="fav p-1">
            <div class="recentCardSection">
                <div v-for="cardinfo in state.articles" :key="cardinfo">
                    <RecentCard :info="cardinfo"></RecentCard>
                </div>
            </div>
        </div>
    </div>
</template>
<script setup>
    import { reactive } from "vue";
    import store from "@/store";
    import axios from "axios";
    import RecentCard from "@/components/RecentCard.vue";
    let state = reactive({
        articles: [],
        totalPage: Number.MAX_SAFE_INTEGER,
        page: 0,
    });
    const headers = {
        "Content-Type": "application/json; charset=utf-8",
        Authorization: store.state.token,
        userid: store.state.userid,
    };
    let body = {
        userid: store.state.userid,
        reqPage: state.page,
    };
    function getRecentArticleList(pageNum) {
        if (pageNum >= state.totalPage) {
            return;
        }
        body.reqPage=pageNum
        axios.post(`${store.state.axiosLink}/api/recent/list`, body, { headers }).then(function (res) {
            res.data
            state.articles.push(...JSON.parse(JSON.stringify(res.data.cardInfo)));
            state.totalPage = JSON.parse(JSON.stringify(res.data.totalPage));
            
        });
    }
    function windowSize(){
      if(window.innerHeight+window.scrollY>=document.body.offsetHeight-100){
        state.page+=1
        getRecentArticleList(state.page)
      }
    }
    
    window.addEventListener("scroll", windowSize)
    getRecentArticleList(state.page);
</script>

<style scoped lang="sass">
    .recentCardSection
        display: grid
        grid-template-columns: repeat(auto-fit, minmax(450px, 1fr))
</style>
