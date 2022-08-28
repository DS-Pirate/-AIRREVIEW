<template>
    <div>
        <div class="col-md-12 my-3 w-100">
            <div class="panel-body">
                <textarea class="form-control w-100" rows="2" placeholder="댓글을 입력하세요"></textarea>
                <div class="mar-top clearfix">
                    <button class="btn btn-sm btn-primary pull-right my-2" type="submit">입력</button>
                </div>
            </div>
        </div>
    </div>
    <div ref="commentList"></div>
</template>
<script>
import axios from 'axios'
import { ref } from 'vue'

export default {

    name: "CommentList",
    props: ["comment"],
    setup() {
        let id = new URLSearchParams(window.location.search).get('article');
        let cardCnt = 0
        let comments = []
        let commentLength = 0
        let commentList = ref(null)
        async function getComments() {
            function calcStar(num) {
                let tmp = ""
                for (let i = 0; i < num; i++) {
                    tmp += "★"
                }
                return tmp
            }
            await axios.get(`/airreview/article/comment/${id}`)
                .then(
                    res => {
                        let str = "<hr>"
                        for (let i = 0; i < res.data.commentList.length; i++) {
                            str += `
                            <div class="comment-section w-100 h-100 d-flex justify-content-between gap-2 py-3" style="margin-left:${(res.data.commentList[i].commnetDepth * 3)}rem !important; padding-right:${(res.data.commentList[i].commnetDepth * 3)}rem !important;">
                                <div class="comment-profile h-auto d-flex justify-content-center align-items-start">
                                    <img class ="img-fluid comment-profile-img mt-1" src="./images/read/userid/${res.data.commentList[i].userid}" alt="profile">
                                </div>

                                <div class="comment-content w-95">
                                    <div class="comment-content-userinfo d-flex justify-content-between align-items-center">
                                        <div class="comment-content-userinfo-left d-flex flex-column">
                                            <span class="comment-content-userinfo-left_username">
                                                ${res.data.commentList[i].userName}
                                            </span>
                                            <span class="comment-content-userinfo-left_date text-secondary" style="font-size:0.75rem;">
                                                [3일 전]
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
                                        <span class="comment-content-functions_commentrate d-flex justify-content-center align-items-center">
                                            ${res.data.commentList[i].rate}
                                        </span>
                                        <button class="comment-content-functions_rereplybtn bg-white" style="cursor:pointer;">
                                            Comment
                                        </button>
                                        
                                    </div>
                                </div>
                            </div>
                            <hr>`


                        }
                        commentList.value.innerHTML = str


                    }
                ).catch(
                    e => console.log(e)
                )
        }
        getComments()
        return { cardCnt, comments, commentLength, commentList }
    }
}
</script>
