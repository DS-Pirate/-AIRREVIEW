<template>
    <div class="comment-section d-flex justify-content-between me-4" :style="'margin-left:' + props.cardInfo.commnetDepth*3 + 'rem'">
        <div class="comment-section-left w-10 d-flex flex-column align-items-center">
            <div class="comment-section-left-profile d-flex justify-content-between">
                <!-- 기본 이미지 일단 넣어둠 -->
                <!-- <img class="img-fluid" :src="'./images/read/userid/' + props.cardInfo.userid" style="width: 2.75rem" /> -->
                <img class="img-fluid py-2 px-3" style="max-height: 60px;" src="@/assets/pngwing.com.png" />
            </div>
            <div class="comment-section-left-delete"></div>
        </div>
        <div class="comment-section-right w-90">
            <div class="comment-section-right-commentauthor d-flex justify-content-between">
                <div class="comment-section-right-commentauthor-authorinfo d-flex flex-column gap-1">
                    <div class="comment-section-right-commentauthor-authorinfo_usernamdandregdate d-flex align-items-end gap-1">
                        <span class="comment-section-right-commentauthor-authorinfo_username">{{ props.cardInfo.userName }}</span>
                        <span class="comment-section-right-commentauthor-authorinfo_regdate" style="font-size: 0.7rem; color: gray">{{ getTimeFromJavaDateForComment(props.cardInfo.regDate) }}</span>
                    </div>

                    <span class="comment-section-right-commentauthor-authorinfo_context">{{ props.cardInfo.commentContext }}</span>
                    <div class="comment-section-right-commentauthor-authorinfo-function fs-6 d-flex gap-2">
                        <div class="comment-section-right-commentauthor-authorinfo_commentrating d-flex gap-2 align-items-center">
                            <i class="bi bi-hand-thumbs-up" v-if="props.cardInfo.israted == 0" @click="ratingComment(1)"></i>
                            <i class="bi bi-hand-thumbs-up-fill" v-if="props.cardInfo.israted == 1" @click="ratingComment(1)"></i>
                            <span>{{ props.cardInfo.rate }}</span>
                            <i class="bi bi-hand-thumbs-down" v-if="props.cardInfo.israted == 0" @click="ratingComment(-1)"></i>
                            <i class="bi bi-hand-thumbs-down-fill" v-if="props.cardInfo.israted == -1" @click="ratingComment(-1)"></i>
                        </div>

                        <button class="btn btn-primary" type="button" data-bs-toggle="collapse" :data-bs-target="'#commentreply' + props.cardInfo.cid" aria-expanded="false" aria-controls="commentreply">답글</button>
                    </div>
                </div>
                <div class="comment-section-right-commentauthor_articlerating">
                    <span class="comment-section-right-commentauthor_articlerating_rating" v-html="calcStar(props.cardInfo.articleRate)"></span>
                </div>
            </div>
            <div class="comment-section-right-commentcontext gap-2">
                <form>
                    <div class="collapse" :id="'commentreply' + props.cardInfo.cid">
                        <div class="input-class h-75 mt-2">
                            <input id="rereply" type="text" class="form-control" v-model="commentInfo.reply" />
                        </div>
                        <div class="input-btn-class h-25 py-1 w-100 d-flex justify-content-end">
                            <button class="btn btn-primary replyReplySubmit my-1" style="cursor: pointer" type="button" @click="addCommentReply">등록</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <hr />
</template>
<script setup>
    import { defineProps } from "vue";
    import { useRouter } from "vue-router";
    import { useStore } from 'vuex'
    import axios from "axios";
    let props = defineProps(["cardInfo"]);
    const router = useRouter();
    const store = useStore()
    const headers = {
        "Content-Type": "application/json; charset=utf-8",
        "Authorization": store.state.token,
        "userid" : store.state.userid
    };

    let commentInfo = {
        reply: null,
    };

    async function ratingComment(ud) {
        let body = {
            userid: store.state.userid,
            commentUserid: props.cardInfo.userid,
            cid: props.cardInfo.cid,
            updown: ud,
        };
        await axios
            .post("./api/article/comment/add/rating/", body, { headers })
            .then((res) => console.log("레이팅들어간다아아아아", res))
            .catch((e) => console.log(e));
        router.go(0);
    }

    function calcStar(num) {
        let tmp = "";
        for (let i = 0; i < num; i++) {
            tmp += `<i class="bi bi-star-fill"></i>`;
        }
        return tmp;
    }

    async function addCommentReply() {
        const headers = {
            "Content-Type": "application/json; charset=utf-8",
            "Authorization": store.state.token,
            "userid" : store.state.userid
        };
        let body = {
            email: store.state.email,
            aid: new URLSearchParams(window.location.search).get("article"),
            commentGroup: props.cardInfo.commentGroup,
            commnetDepth: props.cardInfo.commnetDepth+1,
            commentSorts: props.cardInfo.commentSorts+1,
            commentContext: commentInfo.reply,
        };
        await axios
            .post("./api/article/comment/add/reply", body, { headers })
            .then((res) => console.log("대댓들어간다아아아", res))
            .catch((e) => console.log(e));
        router.go(0);
    }

    function getTimeFromJavaDateForComment(s) {
        const cont = new Date(s);
        let date = new Date();
        let calculated = (new Date(date.getTime()) - cont) / 1000;
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
</script>
<style scoped lang="sass">
    .comment-section-right-commentauthor_articlerating_rating
        white-space: nowrap
    .comment-section-right-commentauthor-authorinfo_commentrating i
        cursor: pointer
</style>
