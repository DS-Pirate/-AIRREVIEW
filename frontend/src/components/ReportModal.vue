<template>
    <div class="modal fade" id="ReportModal" tabindex="-1" aria-labelledby="ReportModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="ReportModalLabel">글 신고</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="modal-body-article">

                    </div>
                    <select class="form-select" aria-label="ReportSelect" v-model="categories" @Change="selectedValue($event.target.value)">
                        <option selected>신고사유를 선택하세요</option>
                        <option v-for="category in categories" :key="{value: category.value, text:category.name}" >{{category.name}}</option>
                    </select>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                    <button type="button" class="btn btn-primary" @click="submitReport">보내기</button>
                </div>
            </div>
        </div>
    </div>
</template>
<script setup>
import axios from 'axios';
import { defineProps } from "vue";
import { useRouter } from 'vue-router'
    const props = defineProps(['id'])
    const router = useRouter()
    let categories= [
        {value:1, name:"성적인 콘텐츠"},
        {value:2 ,name:"폭력적 또는 혐오스러운 콘텐츠"},
        {value:3 ,name:"증오 또는 악의적인 콘텐츠"},
        {value:4 ,name:"희롱 또는 괴롭힘"},
        {value:5 ,name:"유해하거나 위험한 행위"},
        {value:6 ,name:"잘못된 정보"},
        {value:7 ,name:"아동 학대"},
        {value:8 ,name:"테러 조장"},
        {value:9 ,name:"스팸 또는 오해의 소지가 있는 콘텐츠"},
        {value:10 ,name:"권리 침해"}
]
    let selected = null
    function selectedValue(i){
        selected = i
        console.log(selected);
    }

    async function submitReport(){
        const url="./api/article/report"
        const headers = {
                "Content-Type": "application/json; charset=utf-8",
                "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjIwMDY2NjUsImV4cCI6MTY2NDU5ODY2NSwic3ViIjoiMWFhYUBhYWEuY29tIn0.SLdsL0VW2nyHEwkrAAqqn6uvUmpqMSHbUg81530SQvA",
            }
            let body = {
                //store에서 userid획득
                userid:1,
                articleid:props.id,
                reportContext:selected
            }
            await axios.post(url, body, { headers })
            .then(res=>alert(res.data))
            .catch(e=>console.log(e))
            
            router.go(0)
    }

</script>
