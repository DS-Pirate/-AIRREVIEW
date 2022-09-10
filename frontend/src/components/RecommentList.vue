<template>
    <section class="recommend w-15">
        <span class="title">추천 게시글</span>

        <div class="row-cols-1 py-4" v-for="(info, idx) in articleStat" v-bind:key="idx">
            <Cards v-bind:cardInfo="info" v-bind:uInfo="userInfo[idx]" v-if="props.id!=info.aid"></Cards>
            <span v-else><hr></span>
        </div>
    </section>

</template>
<script setup>
    import axios from "axios";
    import { defineProps, reactive } from "vue";
    import Cards from "./ArticleRecommendCard.vue";
    let articleStat = reactive([])
    let artiInfo = reactive([])
    let userInfo = reactive([])
    let props = defineProps(["id"]);
    let body = reactive({
        aid: props.id,
        reqPage: 0,
    });

    function updateStates(aState, aInfo, uInfo) {
        articleStat.push(...aState)
        artiInfo.push(aInfo)
        userInfo.push(...uInfo)
    }

    function getCardInfoByHashTagWithAid() {
        axios
            .post("./article/articlerecommend/", body)
            .then(function (res) {
                if (res.data.pageTotalCount == artiInfo.reqPage + 1) {
                    updateStates(res.data.articles, -999, res.data.userInfo);
                } else {
                    updateStates(res.data.articles, res.data.articles.length, res.data.userInfo);
                }
            })
            .catch(function (error) {
                console.log(error);
                updateStates(null, -999);
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
