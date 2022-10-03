<template>
    <metainfo />
    <div class="readReview justify-content-between">
        <section class="info w-100">
            <div class="metadata">
                <div class="title mb-3">
                    <h1 style="display: inline; font-family: Pretendard-Regular;font-weight: 800;">{{ articleinfo.atitle }}</h1> &nbsp;
                    <h1 style="display: inline;" v-html="articleinfo.starIcon"></h1>
                </div>

                <div class="hashAndStar d-flex flex-row mb-3">
                  <!--   별-->
<!--                        <div v-html="articleinfo.starIcon"></div>-->
                        <div class="p-1" style="font-size: 0.9rem; color: #aaa">
                            <span> 조회수 {{ articleinfo.opencount }}</span>
<!--                            <span class="rating-stars-section_opencount_num">{{ articleinfo.opencount }}</span>-->
                        </div>
                  <div class="pt-1 pb-1" style="font-size: 0.9rem; color: #aaa"><span> · </span></div>
                        <div class="p-1" style="font-size: 0.9rem; color: #aaa">
                          <span> 현재평점 {{ articleinfo.articleAVG }} </span>
                          <!--                            <span class="rating-stars-section_accuraterating_num">{{ articleinfo.articleAVG }}</span>-->
                        </div>
                </div>
                <hr />
                <div class="contentAndButton mt-5 pt-5">
                    <div class="reviewContent clamp" v-html="articleinfo.context"></div>
                </div>

              <div class="d-flex mb-3 mt-5 pt-5">
                <div style="display: inline;" class="" v-for="(a, idx) in articleinfo.tag" :key="idx">
                  <button class="btntag" style="border: 1px solid #e7e7e7" @click="tagbtn(a)">#{{a}}</button>
                </div>
                <span style="display: inline;" class="views ms-auto p-2">{{ articleinfo.regdate }}</span>
              </div>

            </div>
            <hr />
            <ArticleBtnSection :id="id"></ArticleBtnSection>
            <ArticleUserCard :id="id"></ArticleUserCard>
            <ArticleCommentList></ArticleCommentList>
            <ReportModal :id="id"></ReportModal>
        </section>
        <RecommentList :id="id"></RecommentList>
    </div>
</template>

<script setup>
    import axios from "axios";
    import { reactive } from "vue";
    import { useRouter } from "vue-router";
    import ArticleCommentList from "@/components/ArticleCommentList.vue";
    import ReportModal from "../components/ReportModal.vue";
    import ArticleUserCard from "@/components/ArticleUserCard.vue";
    import RecommentList from "@/components/RecommentList.vue";
    import ArticleBtnSection from "@/components/ArticleBtnSection.vue";
    import { useMeta } from "vue-meta";
    import store from "@/store";

    let id = new URLSearchParams(window.location.search).get("article");
    store.commit("setArticleId", id)

            let articleinfo = reactive({
                aid: null,
                atitle: "",
                context: null,
                opened: null,
                shareable: null,
                tags: [],
              tag: [],
              userId: null,
                regdate: null,
                images: null,
                articleAVG: null,
                starIcon: null,
                opencount: null,
              dateformat:null
            });
            const { meta } = useMeta({
                title: "에어리뷰",
                titleTemplate: " :: 에어리뷰",
                meta: [

                ],
            });

            if (id.length == 0) {
                errorAndGetBack();
            }

            const router = useRouter();
            function errorAndGetBack() {
                alert("잘못된 접근입니다");
                router.push("/");
            }
            function getTimeFromJavaDate(s) {
                const cont = new Date(s);
                let date = new Date();
                let calculated = (new Date(date.getTime()) - cont) / 1000; //초 계산
                if (calculated < 60) {
                    return "방금 전";
                } else if (calculated < 60 * 60) {
                    return `${Math.round(calculated / 60)}분 전`;
                } else if (calculated < 60 * 60 * 24) {
                    return `${Math.round(calculated / (60 * 60))}시간 전`;
                } else if (calculated < 60 * 60 * 24 * 7) {
                    return `${Math.round(calculated / (60 * 60 * 24))}일 전`;
                } else if (calculated < 60 * 60 * 24 * 7 * 5) {
                    return `${Math.round(calculated / (60 * 60 * 24 * 7))}주 전`;
                } else if (calculated > 31536000) {
                    return `${Math.round(calculated / 31536000)}년 전`;
                }
            }
            axios.get(`${store.state.axiosLink}/article/read/${id}`).then((res) => {
                articleinfo.aid = res.data.articleInfo.aid;
                articleinfo.atitle = res.data.articleInfo.atitle; //
                articleinfo.context = res.data.articleInfo.context; //
                articleinfo.opened = res.data.articleInfo.opened;
                articleinfo.shareable = res.data.articleInfo.shareable;
                articleinfo.tags = res.data.articleInfo.tags;
                articleinfo.tag = res.data.articleInfo.tags;
              articleinfo.userId = res.data.articleInfo.userId;
                articleinfo.regdate = getTimeFromJavaDate(res.data.articleInfo.regdate);
              articleinfo.dateformat = res.data.articleInfo.regdate;
              articleinfo.images = res.data.articleInfo.images;
                articleinfo.articleAVG = res.data.articleAVG != undefined ? res.data.articleAVG.toFixed(2) : 0;
                articleinfo.opencount = res.data.articleInfo.opencount
                store.commit("setAuthorid", articleinfo.userId)

                    meta.title = res.data.articleInfo.atitle + " :: 세상의 모든 리뷰";
                    const box = document.createElement("div");
                    box.innerHTML = res.data.articleInfo.context
                    let contextText = box.innerText
                    meta.meta.push(                    
                    res.data.articleInfo.images.lenth>0?{
                        type: "og:image",
                        description: "",
                        image: `${store.state.axiosLink}/images/read/${res.data.articleInfo.images[0]}`,
                        //나중에 변경
                    }:"",
                    {
                        property: "og:description",
                        content: `${contextText}`,
                        // vmid: "og:description",
                    },
                    {
                        property: "og:url",
                        content: document.location.href,
                        // vmid: "og:url",
                    },
                    {
                        property: "og:article",
                        content: "article",
                        // vmid: "og:type",Author
                    },
                    {
                        property: "og:Keywords",
                        content: `${[...res.data.articleInfo.tags]}`,

                    },
                    
                    
                    )
                    


                    

                let tmp = [];
                let tag = [];
                for (let i = 0; i < Math.round(res.data.articleAVG); i++) {
                    tmp.push(`<i class="bi bi-star-fill"></i>`);
                }
                for (let i = 0; i < 5 - Math.round(res.data.articleAVG); i++) {
                    tmp.push(`<i class="bi bi-star"></i>`);
                }
                for (let element of res.data.articleInfo.tags) {
                    tag.push(`<span>#${element}<span>`);
                }
                if (articleinfo.opened == false) {
                    alert("비공개글입니다");
                    router.go(-1);
                }
                articleinfo.tags = tag.join("  ");
                articleinfo.starIcon = tmp.join("");
            });

             async function tagbtn(a){
              await router.push(`/search?cards=${a}&order=new`);
               await router.go(0);
            }
    
</script>

<style lang="sass">

.btntag
  font-family: Pretendard-Regular
  border: 1px solid #e7e7e7
  background-color: #fff
  border-radius: 1.5rem
  box-sizing: border-box
  color: #8c8c8c
  cursor: pointer
  display: inline-block
  font-size: 0.9rem
  font-weight: 400
  line-height: 1
  padding: 0.5rem 0.9rem
  text-align: center
  text-decoration: none #0d172a solid
  text-decoration-thickness: auto
  transition: all .1s cubic-bezier(.4, 0, .2, 1)
  user-select: none
  -webkit-user-select: none
  touch-action: manipulation

</style>