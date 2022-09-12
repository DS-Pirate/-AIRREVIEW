<template>
    <div class="popover__wrapper">
        <i class="bi bi-bell"></i>
        <div class="popover__content py-3">
            <div class="text-center pb-3 border border-0 border-bottom">새소식</div>
            <div class="popover__context">
                <AlarmPopoverCard v-for="info in cardInfo.info" :key="info" :info="info" class="border border-0 border-bottom"></AlarmPopoverCard>
            </div>
        </div>
    </div>
</template>
<script setup>
    import AlarmPopoverCard from "./AlarmPopoverCard.vue";
    import { useStore } from "vuex";
    import { reactive } from "vue"
    import axios from "axios";
    const store = useStore();
    const url = "./api/alarm/list";
    const headers = {
        "Content-Type": "application/json; charset=utf-8",
        Authorization: store.state.token,
        userid: store.state.userid,
    };

    let body = {
        userid : store.state.userid
    }

    let cardInfo = reactive({
        info: null
    })

    getTimeFromJavaDateForComment(0)
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




    axios.post(url, body, { headers })
    .then(function(res){
        cardInfo.info = res.data
    })

</script>
<style scoped lang="sass">
    .popover__wrapper
        position: relative

    .popover__content
        right:1%
        border-radius: 0.2rem
        opacity: 0
        visibility: hidden
        position: absolute
        background-color: white
        box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.25)
        width: 25rem

        transition: all 0.2s cubic-bezier(0.75, -0.02, 0.2, 0.97)


    .popover__content:before
        right:1%
        position: absolute
        z-index: -1

    .popover__wrapper:hover .popover__content
        right:1%
        z-index: 10
        opacity: 1
        visibility: visible
</style>
