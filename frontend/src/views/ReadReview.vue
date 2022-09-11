<template>
    <div class="readReview justify-content-between">
        <section class="info w-100">
            <div class="metadata">
                <div class="title">
                    <h1 ref="Iatitle"></h1>
                </div>
                <hr />
                <div class="hashAndStar d-flex justify-content-between">
                    <ul class="hash m-0 p-0 px-1 d-flex align-items-center" ref="Itags"></ul>
                    <div class="rating-stars-section">
                        <div ref="stars"></div>
                        <div class="ating-stars-section_accuraterating d-flex justify-content-between" style="font-size: 0.1rem; color: #aaa">
                            <span>현재평점</span>
                            <span class="rating-stars-section_accuraterating_num" ref="ratingnum"></span>
                        </div>
                    </div>
                </div>
                <hr />
                <div class="contentAndButton">
                    <div class="reviewContent clamp" ref="Icontext"></div>
                </div>
                <span class="views" ref="Iregdate"></span>
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
    import { ref } from "vue";
    import { useRouter } from "vue-router";
    import ArticleCommentList from "@/components/ArticleCommentList.vue";
    import ReportModal from "../components/ReportModal.vue";
    import ArticleUserCard from "@/components/ArticleUserCard.vue";
    import RecommentList from "@/components/RecommentList.vue";
    import ArticleBtnSection from "@/components/ArticleBtnSection.vue";
    const router = useRouter();
    const Iatitle = ref(null);
    const Icontext = ref(null);
    const Itags = ref(null);
    const Iregdate = ref(null);
    let stars = ref(null);
    let ratingnum = ref(null);
    let articleInfo = null;
    let id = new URLSearchParams(window.location.search).get("article");

    if (id.length == 0) {
        errorAndGetBack();
    }

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

    async function getArticleInformation() {
        await axios
            .get(`/airreview/article/read/${id}`)
            .then((res) => {
                articleInfo = res.data.articleInfo;
                Iatitle.value.innerText = articleInfo.atitle;
                Icontext.value.innerHTML = articleInfo.context;
                Iregdate.value.innerText = getTimeFromJavaDate(articleInfo.regdate);
                ratingnum.value.innerText = res.data.articleAVG != undefined ? res.data.articleAVG.toFixed(2) : 0;
                let tmp = [];
                for (let element of articleInfo.tags) {
                    tmp.push(`<span>#${element}<span>`);
                }
                Itags.value.innerHTML = tmp.join(" ");
                let tmp2 = [];
                for (let i = 0; i < Math.round(res.data.articleAVG); i++) {
                    tmp2.push(`<i class="bi bi-star-fill"></i>`);
                }
                for (let i = 0; i < 5 - Math.round(res.data.articleAVG); i++) {
                    tmp2.push(`<i class="bi bi-star"></i>`);
                }
                stars.value.innerHTML = tmp2.join("");
            })
            .catch((e) => {
                //에러 처리는 더 궁리 필요
                if (e.name == "AxiosError") {
                    errorAndGetBack();
                } else {
                    console.log("loadded!");
                    console.log(e);
                }
            });
    }

    getArticleInformation();
</script>
