<template>
    <div class="popover__wrapper">
        <i class="bi bi-bell"></i>
        <div class="popover__content py-3">
            <div class="text-center pb-3 border border-0 border-bottom">{{store.state.isAlarm}}건의 새소식</div>
            <div class="popover__context">
                <AlarmPopoverCard  v-for="info in cardInfo.info" :key="info" :info="info" :getAlarm="getAlaram" class="border border-0 border-bottom popvoer__context__card"></AlarmPopoverCard>
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
    const url = store.state.axiosLink+"/api/alarm/list";
    const headers = {
        "Content-Type": "application/json; charset=utf-8",
        Authorization: store.state.token,
        userid: store.state.userid,
    };

    let body = {
        userid : store.state.userid
    }

    let cardInfo = reactive({
        info: null,
        availableAlarm : 0
    })

    function getAlaram(){
    axios.post(url, body, { headers })
    .then(function(res){
        cardInfo.info = res.data
        for (const key in cardInfo.info) {
            if (Object.hasOwnProperty.call(cardInfo.info, key)) {
                const element = cardInfo.info[key];
                if(element[6] == false){
                    cardInfo.availableAlarm+=1
                }
            }
        }
        store.commit("setAlarm", cardInfo.availableAlarm)
    })
}
getAlaram()
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
        .popvoer__context__card
            position: relative
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
