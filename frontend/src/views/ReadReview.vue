<template>
    <metainfo />
    <div class="readReview justify-content-between">
        <section class="info w-100">
            <div class="metadata">
                <div class="title">
                    <h1>{{ articleinfo.atitle }}</h1>
                </div>
                <hr />
                <div class="hashAndStar d-flex justify-content-between">
                    <ul class="hash m-0 p-0 px-1 d-flex align-items-center" v-html="articleinfo.tags"></ul>
                    <div class="rating-stars-section">
                        <div v-html="articleinfo.starIcon"></div>
                        <div class="rating-stars-section_accuraterating d-flex justify-content-between" style="font-size: 0.1rem; color: #aaa">
                            <span>현재평점</span>
                            <span class="rating-stars-section_accuraterating_num">{{ articleinfo.articleAVG }}</span>
                        </div>                        
                        <div class="rating-stars-section_opencount d-flex justify-content-between" style="font-size: 0.1rem; color: #aaa">
                            <span>조회수</span>
                            <span class="rating-stars-section_opencount_num">{{ articleinfo.opencount }}</span>
                        </div>
                    </div>
                </div>
                <hr />
                <div class="contentAndButton">
                    <div class="reviewContent clamp" v-html="articleinfo.context"></div>
                </div>
                <span class="views">{{ articleinfo.regdate }}</span>
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

            let articleinfo = reactive({
                aid: null,
                atitle: "",
                context: null,
                opened: null,
                shareable: null,
                tags: [],
                userId: null,
                regdate: null,
                images: null,
                articleAVG: null,
                starIcon: null,
                opencount: null
            });
            const { meta } = useMeta({
                title: "에어리뷰",
                titleTemplate: " :: 에어리뷰",
                meta: [

                ],
            });
            let id = new URLSearchParams(window.location.search).get("article");
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
            axios.get(`./article/read/${id}`).then((res) => {
                articleinfo.aid = res.data.articleInfo.aid;
                articleinfo.atitle = res.data.articleInfo.atitle; //
                articleinfo.context = res.data.articleInfo.context; //
                articleinfo.opened = res.data.articleInfo.opened;
                articleinfo.shareable = res.data.articleInfo.shareable;
                articleinfo.tags = res.data.articleInfo.tags;
                articleinfo.userId = res.data.articleInfo.userId;
                articleinfo.regdate = getTimeFromJavaDate(res.data.articleInfo.regdate);
                articleinfo.images = res.data.articleInfo.images;
                articleinfo.articleAVG = res.data.articleAVG != undefined ? res.data.articleAVG.toFixed(2) : 0;
                articleinfo.opencount = res.data.articleInfo.opencount
                store.commit("setAuthorid", articleinfo.userId)

                    meta.title = res.data.articleInfo.atitle + " :: 세상의 모든 리뷰";
                    const box = document.createElement("div");
                    box.innerHTML = res.data.articleInfo.context
                    let contextText = box.innerText
                    // meta.type = "article";
                    // meta.description = res.data.articleInfo.context;
                    // meta.url = document.location.href;
                    // meta.image = `${document.location.href}:9090/images/read/`;
                    meta.meta.push(                    
                    res.data.articleInfo.images.lenth>0?{
                        type: "og:image",
                        description: "",
                        url: document.location.href,
                        image: `${document.location.href}:9090/images/read/${res.data.articleInfo.images[0]}`,
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
    
</script>
