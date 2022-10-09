<template>
    <div class="embed-background" id="parentpage">
        <div class="sharePreview w-100">
            <div class="preview p-3 mb-2 border">
                <div class="preview-title text-center" v-show="inputarea.title">
                    <h3>{{ embedInfo.title }}</h3>
                </div>
                <hr />
                <div class="preview-context d-flex gap-2 align-items-top">
                    <img :src="`https://hyns.co.kr/images/read/${embedInfo.img}`" alt="image" class="w-30 preview-context-thumbnailimg img-fluid" v-show="inputarea.thumbnail" />
                    <div class="preview-context-context w-70 d-flex flex-column gap-3">
                        <span class="preview-context-context-rating" v-show="inputarea.rating">★★★★★</span>
                        <p class="preview-context-context_description" v-html="embedInfo.context"></p>
                    </div>
                </div>
                <hr />
                <div class="preview-footer d-flex justify-content-between px-3">
                    <div class="preview-footer-logo">
                        <span v-show="inputarea.logo" class="preview-footer-logo_text">에어리뷰</span>
                    </div>
                    <div class="preview-footer-etcinfo d-flex gap-3">
                        <span v-show="inputarea.author" class="preview-footer-etcinfo_author">{{ embedInfo.username }} 저</span>
                        <span v-show="inputarea.author" class="preview-footer-etcinfo_verticalbar">|</span>
                        <span v-show="inputarea.like" class="preview-footer-etcinfo_likecount">♥{{ embedInfo.fav }}</span>
                        <span v-show="inputarea.like" class="preview-footer-etcinfo_verticalbar">|</span>
                        <span v-show="inputarea.date" class="preview-footer-etcinfo_reddate">{{ embedInfo.regdate }}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script setup>
    import axios from "axios";
    import { reactive } from "vue";
    import store from "@/store";
    let id = new URLSearchParams(window.location.search).get("article");
    let title = new URLSearchParams(window.location.search).get("title");
    let thumbnail = new URLSearchParams(window.location.search).get("thumbnail");
    let date = new URLSearchParams(window.location.search).get("date");
    let rating = new URLSearchParams(window.location.search).get("rating");
    let like = new URLSearchParams(window.location.search).get("like");
    let logo = new URLSearchParams(window.location.search).get("logo");
    let author = new URLSearchParams(window.location.search).get("author");

    let inputarea = reactive({
        title: title,
        thumbnail: thumbnail,
        date: date,
        rating: rating,
        like: like,
        logo: logo,
        author: author,
    });

    let embedInfo = reactive({
        regdate: null,
        title: null,
        context: null,
        username: null,
        fav: null,
        avg: null,
        img: null,
    });

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


    axios.get(`${store.state.axiosLink}/info/${id}`).then(function (res) {
        let info = res.data;
        embedInfo.regdate = getTimeFromJavaDate(info.regdate)
        embedInfo.title = info.title
        embedInfo.username = info.author
        embedInfo.fav = info.favcount
        embedInfo.avg = info.avgrate
        embedInfo.context = info.context
        embedInfo.img = info.imageList[0].replace(/['|"|<|>|]|&lt|&gt|\\/g, "")

        if (embedInfo.context.indexOf("<iframe") > 0) {
            embedInfo.context = embedInfo.context.replace(embedInfo.context.slice(embedInfo.context.indexOf("<iframe"), embedInfo.context.indexOf("</iframe>") + 9), `>> ${id}번 글 링크<br>`);
        }
    });
</script>
<style lang="sass">
    *
        -ms-overflow-style: none
        scrollbar-width: none
        overflow: visible
        &::-webkit-scrollbar
            display: none
    .subnav, .routing-header
        display: none !important

    .sharePreview
        background-color: white
        position: absolute
        top: 0
        left: 0
        z-index: 9999
    a
        color: inherit
        transition: 0.2s
</style>
