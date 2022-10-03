<template>
    <section class="recommend w-15">
        <span class="title">추천 게시글</span>
        <div class="row-cols-1 py-4" v-for="(info, idx) in state.hashInfo || []" v-bind:key="idx">
            <Cards :cardInfo="info"></Cards>
        </div>
    </section>

</template>
<script setup>
    import axios from "axios";
    import { defineProps, reactive } from "vue";
    import Cards from "./ArticleRecommendCard.vue";
    let state = reactive({
        hashInfo : [],
        pageTotalCount:null,
        currentPage:null,
        tmpList: []
    })
    let props = defineProps(["id"]);
    let body = reactive({
        aid: props.id,
        reqPage: 0,
    });

    function appendListToState(res){
        state.pageTotalCount = res.pageTotalCount
        state.currentPage = res.page+=1
        console.log("------------------");
        console.log(res);
        for (let i = 0; i < res.articles.length; i++) {
            let element = res.articles[i];
            element.images.push(res.ImageList[i])
            if(element.aid!=props.id && !state.tmpList.includes(element.aid)){
                state.hashInfo.push(element)
                state.tmpList.push(element.aid)
            }
        }
        console.log("------------------");

    }


    function getCardInfoByHashTagWithAid() {
        if(state.currentPage>state.pageTotalCount){
            return false
        }
        axios
            .post("./article/articlerecommend/", body)
            .then(function (res) {
                appendListToState(res.data)
                
            })
            .catch(function (error) {
                console.log(error)
            });
    }

    function getMoreRecommend(){
        body.reqPage+=1
        getCardInfoByHashTagWithAid()
    }

    window.addEventListener("scroll", windowSize)
    function windowSize(){
        if(window.innerHeight+window.scrollY>=document.body.offsetHeight){
            getMoreRecommend()
        }
        
    }
    getCardInfoByHashTagWithAid();
    console.log(state);
</script>
