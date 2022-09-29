<template>
    <section class="recommend w-15">
        <span class="title">추천 게시글</span>
        <div class="row-cols-1 py-4" v-for="(info, idx) in state.hashInfo" v-bind:key="idx">
            <Cards v-bind:cardInfo="info.articles[info.articles.length-1]" v-if="props.id!=info.aid" :uImage="info.ImageName" :uInfo="info.userInfo[0]"></Cards>
        </div>
    </section>

</template>
<script setup>
    import axios from "axios";
    import { defineProps, reactive } from "vue";
    import Cards from "./ArticleRecommendCard.vue";
    let state = reactive({
        hashInfo : []
    })
    let props = defineProps(["id"]);
    let body = reactive({
        aid: props.id,
        reqPage: 0,
    });



    function getCardInfoByHashTagWithAid() {
        axios
            .post("./article/articlerecommend/", body)
            .then(function (res) {
                state.hashInfo.push(res.data)
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
</script>
