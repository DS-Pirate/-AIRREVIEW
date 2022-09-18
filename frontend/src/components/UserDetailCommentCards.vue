<template>
    <div class="detailcontainer">
        <div class="detailcontainer-header">
            <a :href="'read?article='+props.list.aid"><ul class="detailcontainer-header-list d-flex gap-2 m-0 p-0">
                <li class="detailcontainer-header-list_articleid">{{props.list.aid}}번 글</li>
                <li class="detailcontainer-header-list_rating">{{getStars(props.list.articleRate)}}</li>
                <li class="detailcontainer-header-list_regdate">{{getTimeFromJavaDate(props.list.regDate)}}</li>
                <li><i class="bi bi-arrow-right"></i></li>
            </ul></a>
        </div>
        <div class="detailcontainer-context w-100 px-2">
            <p class="detailcontainer-context_description"><i class="bi bi-arrow-return-right"></i> {{props.list.commentContext}}</p>
        </div>
        
    </div>
</template>
<script setup>
    import {defineProps} from 'vue'

    const props = defineProps(["list"])

    function getStars(num){
        let tmp = []
        for (let i = 0; i < num; i++) {
            tmp.push("★")
        }
        return tmp.join("")
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
</script>
<style scoped lang="sass">
    a
        text-decoration: none
        color: inherit
    .detailcontainer-header-list
        font-size: 0.5rem
        opacity: 0.9
    .detailcontainer-context_description
        font-size: 0.8rem
        white-space: nowrap
        overflow: hidden
        text-overflow: ellipsis
</style>