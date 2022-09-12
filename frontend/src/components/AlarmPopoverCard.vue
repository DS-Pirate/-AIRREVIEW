<template>
    <a :href="'./read?article='+props.info[2]">
        <div class="popovercard px-2">
            <div class="popovercard-profilesection d-flex py-3">
                <div class="popovercard-profilesection__left w-15 d-flex align-items-center">
                    <img class="img-fluid p-2" src="@/assets/pngwing.com.png" alt="" srcset="" />
                </div>
                <div class="popovercard-profilesection__right w-85 d-flex gap-1">
                    <div class="popovercard-profilesection__right__context w-80">
                        <div class="popovercard-profilesection__right__context__user">{{ props.info[0] }}님이 {{ props.info[1] }}님에게 댓글을 남겼습니다</div>
                        <div class="popovercard-profilesection__right__context__context">"{{ props.info[4] }}"</div>
                        <div class="popovercard-profilesection__right__context__title">:: {{ props.info[3] }}</div>
                    </div>
                    <div class="popovercard-profilesection__right__date w-20 px-1 d-flex align-items-center">
                        <span>{{ getTimeFromJavaDateForComment(props.info[5]) }}</span>
                    </div>
                </div>
            </div>
        </div>
    </a>
</template>
<script setup>
    import { defineProps } from "vue";
    let props = defineProps(["info"]);

    getTimeFromJavaDateForComment(0);
    function getTimeFromJavaDateForComment(s) {
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
        } else if (calculated < 31536000) {
            return `${Math.round(calculated / 31536000)}달 전`;
        } else if (calculated > 31536000) {
            return `${Math.round(calculated / 31536000)}년 전`;
        }
    }

    console.log(props.info);
</script>
<style scoped lang="sass">
    a
        text-decoration: none
        color: inherit
    .popovercard-profilesection__right__date
        font-size: 0.85rem
        white-space: nowrap
    .popovercard-profilesection__right__context__context
        color: #bbb
    .popovercard-profilesection__right__context__title
        color: #bbb
</style>
