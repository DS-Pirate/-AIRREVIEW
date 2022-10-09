<template>
    <section class="recommend">
        <span class="title">추천 게시글</span>
        <div class="row-cols-1 py-4 pt-5" v-for="(info, idx) in state.hashInfo || []" v-bind:key="idx">
            <Cards :cardInfo="info"></Cards>
        </div>
        <span v-if="state.hashInfo.length==0"><br><br> 연관 게시글이 없습니다</span>
    </section>

</template>
<script setup>
    import store from "@/store";
import axios from "axios";
    import { defineProps, reactive } from "vue";
    import Cards from "./ArticleRecommendCard.vue";
    let state = reactive({
        hashInfo : [],
        pageTotalCount:null,
        currentPage:null,
        tmpList: [],
    })
    let props = defineProps(["id"]);
    let body = reactive({
        aid: props.id,
        reqPage: 0,
    });

    function appendListToState(res){
        state.pageTotalCount = res.pageTotalCount
        state.currentPage = res.page+=1
        for (let i = 0; i < res.articles.length; i++) {
            let element = res.articles[i];
            element.images.push(res.ImageList[i])
            if(element.aid!=props.id && !state.tmpList.includes(element.aid)){
                element.images[0]= element.images[0].replace(/['|"|<|>|]|&lt|&gt/g, "")
                state.hashInfo.push(element)
                state.tmpList.push(element.aid)
            }
        }

    }


    function getCardInfoByHashTagWithAid() {
        
        if(state.currentPage>state.pageTotalCount){
            return false
        }
        axios
            .post(store.state.axiosLink+"/article/articlerecommend/", body)
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
        if(window.innerHeight+window.scrollY>=document.body.offsetHeight-(window.innerHeight/1.2)){
            getMoreRecommend()
        }
        
    }
    getCardInfoByHashTagWithAid();
</script>
<style scoped lang="sass">
    .recommend
        position: relative
        .title
            animation-duration: 1s
            animation-name: blink
            animation-iteration-count: infinite
            color: #3041a9
            font-size: bold
            position: absolute
            right: 1rem            
            transition: 0.25s ease-in-out all
            &:hover
                animation: none
            
@keyframes blink 
    0% 
        opacity: 0
    50% 
        opacity: 1
    100%
        opacity: 0
  
</style>
