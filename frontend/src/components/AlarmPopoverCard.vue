<template>
    <div class="popovercard px-2" :style="props.info[6] == false ? 'opacity:1;' : 'opacity:0.3;'">
        <a :href="'./read?article=' + props.info[2]" @click="checked(props.info[7])">
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
                    <div class="popovercard-profilesection__right__date w-20 px-1 d-flex align-items-center flex-column">
                        <span>{{ getTimeFromJavaDateForComment(props.info[5]) }}</span>
                        <span v-if="props.info[6] == true">읽음</span>
                    </div>
                </div>
            </div>
        </a>
        <i class="bi bi-x-lg" @click="deleteAlarm(props.info[7])"></i>
    </div>
</template>
<script setup>
    import router from "@/router";
import axios from "axios";
    import { defineProps } from "vue";
    import { useStore } from "vuex";
    let props = defineProps(["info", "getAlaram"]);
    const store = useStore();
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

    const headers = {
        "Content-Type": "application/json; charset=utf-8",
        Authorization: store.state.token,
        userid: store.state.userid,
    };
    const url = store.state.axiosLink+"/api/alarm/checked";

    function checked(i) {
        axios.post(url, i, { headers });
    }

    function deleteAlarm(i){
        axios.post(store.state.axiosLink+"/api/alarm/delete", i, { headers })
        router.go(0)
    }
</script>
<style scoped lang="sass">
    .popovercard
        position: relative
        i
            position: absolute
            z-index: 9999
            top: 50%
            right: 8%
            cursor: pointer
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
