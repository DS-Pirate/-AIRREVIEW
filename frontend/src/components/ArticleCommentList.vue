<template>
    <div>
        <div class="col-md-12 my-3 w-100">
            <div class="panel-body">
                <textarea class="form-control w-100" rows="2" ref="commentcontext" placeholder="댓글을 입력하세요"></textarea>
                <div class="mar-top clearfix d-flex justify-content-end align-items-center">
                    <span class="me-2">별점 : </span>
                    <div class="comment-input-stars fs-5 me-3 pb-1 d-flex justify-content-end align-items-center">
                        <div v-for="i in 5" :key="i" v-on:click="clicked(i)">
                            <a class="bi bi-star-fill comment-input-stars_star" v-if="i < articleRating"></a>
                            <a class="bi bi-star comment-input-stars_star" v-if="i >= articleRating"></a>
                        </div>
                    </div>
                    <button class="btn btn-sm btn-primary pull-right my-2" type="button" @click="addNewcomment">입력</button>
                </div>
            </div>
        </div>
    </div>
    <hr />
    <div class="comment-area d-flex flex-column align-items-between">
        <ArticleCommentCard v-for="info in commentState" v-bind:key="info" v-bind:cardInfo="info"></ArticleCommentCard>
        <button class="btn btn-primary w-100"  @click="getMoreComment" v-if="!(stateInfo[stateInfo.length - 1] == -999)">댓글 더 보기</button>
    </div>
</template>
<script setup>
    import { ref, reactive } from "vue"
    import { useRouter } from "vue-router"
    import { useStore } from 'vuex'
    import axios from "axios"
    import ArticleCommentCard from "./ArticleCommentCard.vue"
    const store = useStore()
    const router = useRouter()
    const id = new URLSearchParams(window.location.search).get("article")
    const headers = {
        "Authorization": store.state.token,
        "userid" : store.state.userid
    }

    let articleRating = ref(0)
    let commentcontext = ref(null)
    let commentState = reactive([])
    let stateInfo = reactive([])
    let commentInfo = reactive({
        aid: id,
        reqPage: 0,
        userid: store.state.userid,
    })

    function getMoreComment() {
        commentInfo.reqPage += 1
        getComments()
    }

    function updateStates(cState, sInfo) {
        commentState.push(...cState)
        stateInfo.push(sInfo)
    }

    function clicked(i) {
        this.articleRating = i + 1
    }

    function getComments() {
        axios
            .post(store.state.userid==0 ? `/airreview/article/comment/` : `/airreview/api/article/comment/`, commentInfo, { headers })
            .then(function (res) {
                if (res.data.pageTotalCount == commentInfo.reqPage + 1) {
                    updateStates(res.data.commentList, -999)
                } else {
                    updateStates(res.data.commentList, (res.data.commentList.length))
                }
            })
            .catch(function () {
                
            })
    }

    async function addNewcomment() {
        let body = {
            userid: store.state.userid,
            email: store.state.email,
            aid: id,
            commentContext: commentcontext.value.value,
            articleRate: articleRating.value - 1,
        }
        axios
            .post("./api/article/comment/add/", body, { headers })
            .then((res) => {
                if (res.data == -1) {
                    alert("등록되었습니다")
                    router.go(0)
                } else {
                    alert("이미 리뷰를 등록하였습니다")
                    router.go(0)
                }
            })
            .catch((e) => console.log(e))
    }
    
    getComments()
</script>
