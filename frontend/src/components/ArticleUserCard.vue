<template>
    <div class="channel">
        <div class="metadata">
            <img ref="IuserIMG" src="@/assets/pngwing.com.png" alt="" />
            <div class="info">
                <span class="name" ref="IairName"></span>
                <span class="subscribers" ref="IsubCount"></span>
            </div>
        </div>
        <button class="btn btn-outline-primary" data-is="none" ref="IisSubed" v-on:click="subsFunction"></button>
    </div>
</template>
<script setup>
    import router from "@/router";
    import axios from "axios";
    import { defineProps, ref, onMounted } from "vue";
    import { useStore } from 'vuex'
    const store = useStore()
    const props = defineProps(["id"])
    let IuserIMG = ref(null)
    let IairName = ref(null)
    let IsubCount = ref(null)
    let IisSubed = ref(null)
    const headers = {
            "Content-Type": "application/json; charset=utf-8",
            "Authorization": store.state.token,
            "userid" : store.state.userid
        }
    let body = {
            aid : props.id,
            userid : store.state.userid
    }

    async function subsFunction(){
        if(IisSubed.value.dataset.is!="none"){
            await axios.post("./api/article/read/subcard/add",body, {headers})
            .then(function(res){
                alert(res.data)
                router.go(0)
            })
        }else{
            router.push("./login")
        }
        }

    async function getCardInfo(){

        await axios.post("./article/read/subcard", body, { headers })
        .then(function(res){
            IuserIMG.value.src = res.data.articleUserImg
            IairName.value.textContent = res.data.articleUserName
        
            //나중에 유효성검사할때 천, 만, 십만, 백만단위 생각
            IsubCount.value.textContent = `구독자 ${res.data.subCount}명`
            if(res.data.isgudoked=="true"){
                IisSubed.value.dataset.is = "true"
                IisSubed.value.textContent = "구독 중"
            }else{
                IisSubed.value.dataset.is = "false"
                IisSubed.value.textContent = "구독 하기"
            }
        }).catch(e=>console.log(e))
    }
    onMounted(() => {
        getCardInfo()
    })
</script>
