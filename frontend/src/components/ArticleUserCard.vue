<template>
    <div class="channel">
        <div class="metadata">
            <img ref="IuserIMG" src="@/assets/pngwing.com.png" alt="" />
            <div class="info">
                <span class="name" ref="IairName"></span>
                <span class="subscribers" ref="IsubCount"></span>
            </div>
        </div>
        <button class="btn btn-outline-primary" ref="IisSubed"></button>
    </div>
</template>
<script setup>
    import axios from "axios";
    import { defineProps, ref, onMounted } from "vue";
    const props = defineProps(["id"])
    let IuserIMG = ref(null)
    let IairName = ref(null)
    let IsubCount = ref(null)
    let IisSubed = ref(null)

    async function getCardInfo(){
        const headers = {
            "Content-Type": "application/json; charset=utf-8",
            "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjIwMDY2NjUsImV4cCI6MTY2NDU5ODY2NSwic3ViIjoiMWFhYUBhYWEuY29tIn0.SLdsL0VW2nyHEwkrAAqqn6uvUmpqMSHbUg81530SQvA",
        }
        let body = {
            aid : props.id,
            //store에서 가져와야함
            userid : 1
        }
        axios.post("./article/read/subcard", body, { headers })
        .then(function(res){
        
            console.log(res);
            IuserIMG.value.src = res.data.articleUserImg
            IairName.value.textContent = res.data.articleUserName
        
            //나중에 유효성검사할때 천, 만, 십만, 백만단위 생각
            IsubCount.value.textContent = `구독자 ${res.data.subCount}명`
            if(res.data.isgudoked=="true"){
                IisSubed.value.textContent = "구독 중"
            }else{
                IisSubed.value.textContent = "구독 하기"
            }
            
        }).catch(e=>console.log(e))
    }
    onMounted(() => {
        getCardInfo()
    })
</script>
