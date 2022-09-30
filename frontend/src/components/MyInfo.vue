<template>
    <div class="myInfo container text-center">
        <div class="row d-flex align-items-start justify-content-between">
            <div class="col d-flex flex-column align-items-center">
                <div class="about mb-3">About Me</div>
                <div>{{userinfo.userIntro!=""?userinfo.userIntro:"사용자 소개글이 없습니다"}}
                </div>
            </div>
            <div class="col d-flex flex-column align-items-center">
                <div class="iconDiv" >
                    <div class="iconDivDiv">
                        <img :src="store.state.axiosLink+'/images/read/userid/'+id" alt="icon" class="img-fluid iconCircle">
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="about mb-3">Details</div>
                <div class="detail mb-2">Name:</div>
                <div class="detailContent mb-2">{{userinfo.airName}}</div>
                <div class="detail mb-2">Age:</div>
                <div class="detailContent mb-2">{{userinfo.age}}</div>
            </div>
        </div>
    </div>
</template>

<script setup>
    import store from "@/store";
    import axios from "axios";
    import { reactive } from 'vue'

    let id = new URLSearchParams(window.location.search).get("channel");
    let userinfo = reactive({
        userIntro: null,
        airName:null,
        birthDay:null,
        fileName:null,
        age : null

    })
    const headers = {
        "Content-Type": "application/json; charset=utf-8",
        "Authorization": store.state.token,
        "userid": store.state.userid
    };
    axios.post(store.state.axiosLink+"/mypage/userinfo", id, { headers }).then(function(res){
        userinfo.userIntro = res.data.userIntro 
        userinfo.airName = res.data.airName 
        userinfo.birthDay = new Date(res.data.birthDay)
        userinfo.fileName = res.data.fileName 
        let currentDate = new Date()
        userinfo.age = currentDate.getFullYear() - userinfo.birthDay.getFullYear()
    })

</script>
