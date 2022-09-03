<template>
    <div>
        <div class="col-md-12 my-3 w-100">
            <div class="panel-body">
                <textarea class="form-control w-100" rows="2" ref="commentcontext" placeholder="댓글을 입력하세요"></textarea>
                <div class="mar-top clearfix d-flex justify-content-end align-items-center">
                    <span class="me-2">별점 : </span>
                    <div class="comment-input-stars fs-5 me-3 pb-1 d-flex justify-content-end align-items-center">
                        <div v-for="i in 5" :key="i" v-on:click="clicked(i)">
                            <a class="bi bi-star-fill comment-input-stars_star" v-if="i<articleRating"></a>
                            <a class="bi bi-star comment-input-stars_star" v-if="i>=articleRating"></a>
                        </div>  
                    </div>
                    <button class="btn btn-sm btn-primary pull-right my-2" type="button" @click="addNewcomment">입력</button>
                </div>
            </div>
        </div>
    </div>
    <div ref="commentList"></div>
</template>
<script>
import axios from 'axios'
import { ref } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import router from '@/router'

export default {
    name: "CommentList",
    props: ["comment"],
    setup() {
        let id = new URLSearchParams(window.location.search).get('article');
        let cardCnt = 0
        let comments = []
        let commentLength = 0
        let commentList = ref(null)
        let commentcontext = ref(null)
        const store = useStore()
        const router = useRouter()
        let articleRating = ref(0)

        function clicked(i){
            this.articleRating = i+1
            console.log(articleRating.value);
        }

        

        function getTimeFromJavaDate(s) {
            const tTime = s.split(/\D+/);
            // const cont = new Date(Date.UTC(tTime[0], --tTime[1], tTime[2], tTime[3], tTime[4], tTime[5], tTime[6])-(9 * 60 * 60 * 1000)-((60*9*1000)+10))
            const cont = new Date(Date.UTC(tTime[0], --tTime[1], tTime[2], tTime[3], tTime[4], tTime[5], tTime[6])-(9 * 60 * 60 * 1000))
            let calculated = (new Date() - cont)/1000 //초 계산
            if(calculated<60){
                return "방금 전"
            }else if(calculated<60*60){
                return`${Math.round(calculated/(60))}분 전`
            }else if(calculated<60*60*24){
                return`${Math.round(calculated/(60*60))}시간 전`
            }else if(calculated<60*60*24*7){
                return`${Math.round(calculated/(60*60*24))}일 전`
            }else if(calculated<60*60*24*7*5){
                return`${Math.round(calculated/(60*60*24*7))}주 전`
            }else if(calculated>31536000){
                return`${Math.round(calculated/31536000)}년 전`
            }
        }


        async function getComments() {
            function calcStar(num) {
                let tmp = ""
                for (let i = 0; i < num; i++) {
                    tmp += `<i class="bi bi-star-fill"></i>`
                }
                return tmp
            }
            await axios.get(`/airreview/article/comment/${id}`)
                .then(
                    res => {
                        let str = "<hr>"
                        let counter = 0
                        for (let i = 0; i < res.data.commentList.length; i++) {
                            counter++
                            //<img class ="img-fluid comment-profile-img mt-1" src="./images/read/userid/${res.data.commentList[i].userid}" alt="profile"> 나중에 프로필사진에 첨부
                            str += `
                            <div class="comment-section w-100 h-100 d-flex justify-content-between gap-2 py-3" style="margin-left:${(res.data.commentList[i].commnetDepth * 3)}rem !important; padding-right:${(res.data.commentList[i].commnetDepth * 3)}rem !important;"
                            data-cgroup="${res.data.commentList[i].commentGroup}" data-cdepth="${res.data.commentList[i].commnetDepth}" data-csorts="${res.data.commentList[i].commentSorts}" 
                            ref="cinfo">
                                <div class="comment-profile h-auto d-flex justify-content-center align-items-start">
                                    <img class ="img-fluid comment-profile-img mt-1" src="https://lh3.googleusercontent.com/a-/AFdZucqQDxMr6ZKaN-SnomfpYB8OZgFsFib8qYR3mVVW2g=s83-c-mo" alt="profile">
                                    
                                </div>

                                <div class="comment-content w-95">
                                    <div class="comment-content-userinfo d-flex justify-content-between align-items-center">
                                        <div class="comment-content-userinfo-left d-flex flex-column">
                                            <span class="comment-content-userinfo-left_username">
                                                ${res.data.commentList[i].userName}
                                            </span>
                                            <span class="comment-content-userinfo-left_date text-secondary" style="font-size:0.75rem;">
                                                ${getTimeFromJavaDate(res.data.commentList[i].regDate)}
                                            </span>
                                        </div>
                                        <div class="comment-content-userinfo-right">
                                            <span class="comment-content-userinfo-right_articlerate">
                                                <span class="comment-content-userinfo-right_articlerate_star fs-5">
                                                    ${calcStar(res.data.commentList[i].articleRate)}
                                                </span>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="comment-content-context">
                                        <span class="comment-content-context_description">
                                            ${res.data.commentList[i].commentContext}
                                        </span>
                                    </div>
                                    <div class="comment-content-functions d-flex gap-3 my-1">
                                        <button class="comment-content-functions_upbtn btn btn-outline-dark border border-1">
                                            추천
                                        </button>
                                        <div class="comment-content-functions d-flex justify-content-center align-items-center gap-2 border border-1 p-1 px-2">
                                            <rateup class="bi bi-hand-thumbs-up" style="cursor:pointer;" data-c="${res.data.commentList[i].cid}"></rateup>
                                            ${res.data.commentList[i].rate}
                                            <ratedwon class="bi bi-hand-thumbs-down" style="cursor:pointer;" data-c="${res.data.commentList[i].cid}"></ratedown>
                                        </div>
                                        <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#commentreply${i}" aria-expanded="false" aria-controls="commentreply${i}">
                                            답글
                                        </button>                                        
                                    </div>
                                    <form>
                                        <div class="collapse" id="commentreply${i}">
                                            <div class="input-class h-75">
                                                <input id="rereply${i}" type="text" class="form-control">
                                            </div>
                                            <div class="input-btn-class h-25 py-1 w-100 d-flex justify-content-end">
                                                <custominput class="btn btn-primary replyReplySubmit" style="cursor:pointer;" data-g="${res.data.commentList[i].commentGroup}" data-d="${res.data.commentList[i].commnetDepth+1}" data-s="${res.data.commentList[i].commentSorts+1}" type="button">답글</customInput>
                                            </div>
                                        </div>
                                    </form>

                                </div>
                            </div>
                            <hr>`

                            if (counter == res.data.commentList.length) {
                                store.commit("setcLatestcGroup", res.data.commentList[i].commentGroup)
                            }
                        }
                        commentList.value.innerHTML = str

                    }
                ).catch(
                    e => console.log(e)
                )
        }

        const headers = {
            "Content-Type": "application/json; charset=utf-8",
            "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjIwMDY2NjUsImV4cCI6MTY2NDU5ODY2NSwic3ViIjoiMWFhYUBhYWEuY29tIn0.SLdsL0VW2nyHEwkrAAqqn6uvUmpqMSHbUg81530SQvA",
        }

        async function addNewcomment() {
            let body = {
                //state.email에서 끌고와야함
                email: "1aaa@aaa.com",
                aid: id,
                commentGroup: store.state.latestcGroup,
                commentContext: commentcontext.value.value,
                commentSorts: 0,
                articleRate: articleRating.value-1

            }
            axios.post("./api/article/comment/add/", body, { headers }).then(
                res=>console.log("신호 나이이스",res).catch(e=>console.log(e)),
                router.go(0)
                
            )
        }

        getComments()
        return { cardCnt, comments, commentLength, commentList, addNewcomment, commentcontext, articleRating, clicked, getTimeFromJavaDate}
    }
}
    //쓰기싫었지만 어쩔수없음 흑흑..
    window.onload = function () {
    let result = document.getElementsByTagName("custominput")
    for (let i = 0; i < result.length; i++) {
        result[i].onclick = async() => {
            console.log(result[i]); 
            console.log(result[i].dataset);
            let tmpcommentGroup = result[i].dataset.g
            let tmpcommnetDepth = result[i].dataset.d
            let tmpcommentSorts = result[i].dataset.s
            const headers = {
                "Content-Type": "application/json; charset=utf-8",
                "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjIwMDY2NjUsImV4cCI6MTY2NDU5ODY2NSwic3ViIjoiMWFhYUBhYWEuY29tIn0.SLdsL0VW2nyHEwkrAAqqn6uvUmpqMSHbUg81530SQvA",
            }
            let body = {
                //state.email에서 끌고와야함
                email: "1aaa@aaa.com",
                aid: new URLSearchParams(window.location.search).get('article'),
                commentGroup: tmpcommentGroup,
                commnetDepth: tmpcommnetDepth,
                commentSorts: tmpcommentSorts,
                commentContext: document.getElementById(`rereply${i}`).value
            }
            await axios.post("./api/article/comment/add/reply", body, { headers })
            .then(res=>console.log("대댓들어간다아아아",res))
            .catch(e=>console.log(e))
            
            router.go(0)
        }
    }

    //쓰기싫었지만 어쩔수없음2 흑흑..
    let rateup = document.getElementsByTagName("rateup")
    for (let i = 0; i < result.length; i++) {
        rateup[i].onclick = async() => {
            console.log(rateup[i]);
            console.log(rateup[i].dataset);
            let tmpCommentid=rateup[i].dataset.c;
            let tmpUserid=1

            const headers = {
                "Content-Type": "application/json; charset=utf-8",
                "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjIwMDY2NjUsImV4cCI6MTY2NDU5ODY2NSwic3ViIjoiMWFhYUBhYWEuY29tIn0.SLdsL0VW2nyHEwkrAAqqn6uvUmpqMSHbUg81530SQvA",
            }
            let body = {
                //state.email에서 끌고와야함
                userid : tmpUserid,
                cid : tmpCommentid,
                updown: 1
            }
            await axios.post("./api/article/comment/add/rating/", body, { headers })
            .then(res=>console.log("레이팅들어간다아아아아",res))
            .catch(e=>console.log(e)) 
            router.go(0)
        }
    }
        //쓰기싫었지만 어쩔수없음3 흑흑..
    let rateDown = document.getElementsByTagName("ratedwon")
    for (let i = 0; i < result.length; i++) {
        rateDown[i].onclick = async() => {
            console.log(rateDown[i]);
            console.log(rateDown[i].dataset);
            let tmpCommentid=rateDown[i].dataset.c;
            let tmpUserid=1

            const headers = {
                "Content-Type": "application/json; charset=utf-8",
                "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjIwMDY2NjUsImV4cCI6MTY2NDU5ODY2NSwic3ViIjoiMWFhYUBhYWEuY29tIn0.SLdsL0VW2nyHEwkrAAqqn6uvUmpqMSHbUg81530SQvA",
            }
            let body = {
                //state.email에서 끌고와야함
                userid : tmpUserid,
                cid : tmpCommentid,
                updown: -1
            }
            await axios.post("./api/article/comment/add/rating/", body, { headers })
            .then(res=>console.log("레이팅들어간다아아아아",res))
            .catch(e=>console.log(e))
            
            router.go(0)
        }
    }
    
    

}

</script>

