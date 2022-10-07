<template>
    <div class="modal" id="share" tabindex="-1" aria-labelledby="share" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-xl">
            <div class="modal-content rounded-0">
                <div class="modal-header">
                    <h5 class="modal-title" id="share">글 퍼가기</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="md-nav text-center mt-2 d-flex gap-3 justify-content-center">
                    <span class="link" @click="changeInput(1)">링크로 퍼가기</span>
                    <span>|</span>
                    <span class="embed" @click="changeInput(2)">embed</span>
                    <span>|</span>
                    <span style="cursor: pointer;" @click="changeInput(3)">이미지로 퍼가기</span>
                </div>
                <hr class="my-2" style="opacity: 0.2" />
                <div class="modal-body pt-0 d-flex w-100">
                    <div class="d-flex flex-column justify-content-between w-15" @change="changeInputRealtime">
                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="title" :checked="inputarea.title" @click="inputarea.title = !inputarea.title" />
                            <label class="form-check-label" for="title">제목</label>
                        </div>

                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="thumbnail" :checked="inputarea.thumbnail" @click="inputarea.thumbnail = !inputarea.thumbnail" />
                            <label class="form-check-label" for="thumbnail">썸네일</label>
                        </div>

                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="author" :checked="inputarea.author" @click="inputarea.author = !inputarea.author" />
                            <label class="form-check-label" for="author">글쓴이</label>
                        </div>

                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="regDate" :checked="inputarea.date" @click="inputarea.date = !inputarea.date" />
                            <label class="form-check-label" for="regDate">날짜</label>
                        </div>

                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="rate" :checked="inputarea.rating" @click="inputarea.rating = !inputarea.rating" />
                            <label class="form-check-label" for="rate">별점</label>
                        </div>

                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="like" :checked="inputarea.like" @click="inputarea.like = !inputarea.like" />
                            <label class="form-check-label" for="like">좋아요</label>
                        </div>

                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="logo" :checked="inputarea.logo" @click="inputarea.logo = !inputarea.logo" />
                            <label class="form-check-label" for="logo">로고</label>
                        </div>

                        <div class="d-flex m-0 p-0 align-items-center">
                            <label class="form-check-label w-30" for="width">넓이</label>
                            <input class="form-control w-60" type="text" id="width" required v-model="embedInfo.width" />
                        </div>
                    </div>

                    <div class="preview w-100" ref="previ">
                        <form>
                            <textarea class="content w-100" readonly v-model="inputarea.description" @click="selectAll($event)"></textarea>
                        </form>
                        <div ref="shareModal" class="sharePreview">
                            <div class="preview p-3 mb-2 border">
                                <div class="preview-title text-center" v-show="inputarea.title">
                                    <h3>{{ embedInfo.title }}</h3>
                                </div>
                                <hr />
                                <div class="preview-context d-flex gap-2 align-items-top">
                                    <img :src="`${store.state.EmbedLink}/images/read/${embedInfo.img}`" alt="image" class="w-30 preview-context-thumbnailimg img-fluid" v-show="inputarea.thumbnail" />
                                    <div class="preview-context-context w-70 d-flex flex-column gap-3">
                                        <span class="preview-context-context-rating" v-show="inputarea.rating" v-html="embedInfo.avg"></span>
                                        <p class="preview-context-context_description" v-html="embedInfo.context"></p>
                                    </div>
                                </div>
                                <hr />
                                <div class="preview-footer d-flex justify-content-between px-3">
                                    <div class="preview-footer-logo">
                                        <span v-show="inputarea.logo" class="preview-footer-logo_text">에어리뷰</span>
                                    </div>
                                    <div class="preview-footer-etcinfo d-flex gap-3">
                                        <span v-show="inputarea.author" class="preview-footer-etcinfo_author">{{ embedInfo.username }} 저</span>
                                        <span v-show="inputarea.author" class="preview-footer-etcinfo_verticalbar">|</span>
                                        <span v-show="inputarea.like" class="preview-footer-etcinfo_likecount">♥{{ embedInfo.fav }}</span>
                                        <span v-show="inputarea.like" class="preview-footer-etcinfo_verticalbar">|</span>
                                        <span v-show="inputarea.date" class="preview-footer-etcinfo_reddate">{{ embedInfo.regdate }}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="modal-btn border-0 m-0">복사</button>
                </div>
            </div>
        </div>
    </div>
</template>
<script setup>
    import store from "@/store";
    import axios from "axios";
    import { reactive, ref } from "vue";
    import html2canvas from "html2canvas"
    const { ClipboardItem } = window;
    let id = store.state.articleId
    let shareModal = ref(null)
    function getShareModalImg(){
        html2canvas(shareModal.value).then(function(img){
            img.toBlob(function(blob){
                navigator.clipboard.write([new ClipboardItem({"image/png":blob})])
            })
        })
    }

    function getTimeFromJavaDate(s) {
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
        } else if (calculated > 31536000) {
            return `${Math.round(calculated / 31536000)}년 전`;
        }
    }

    let inputarea = reactive({
        description: "",
        title: true,
        thumbnail: true,
        date: true,
        rating: true,
        like: true,
        logo: true,
        author: true,
    });

    

    let embedInfo = reactive({
        regdate: null,
        title: null,
        context: null,
        username: null,
        fav: null,
        avg: null,
        img: null,
        width: 800,
    });

    function selectAll(e){
        e.target.select()
    }

    axios.get(`${store.state.axiosLink}/info/${id}`).then(function (res) {
        let info = res.data;
        embedInfo.regdate = getTimeFromJavaDate(info.regdate), 
        embedInfo.title = info.title, 
        embedInfo.username = info.author, 
        embedInfo.fav = info.favcount, 
        embedInfo.context = info.context
        embedInfo.img = info.imageList[0].replace(/['|"|<|>|]|&lt|&gt|\\/g, "")
        if(embedInfo.context.indexOf("<iframe")>0){
            embedInfo.context = embedInfo.context.replace(embedInfo.context.slice(embedInfo.context.indexOf("<iframe"), embedInfo.context.indexOf("</iframe>")+9), `>> ${id}번 글 링크<br>`)
        }


        let tmp = [];
        for (let i = 0; i < Math.round(info.avgrate); i++) {
            tmp.push(`<i class="bi bi-star-fill"></i>`);
        }
        for (let i = 0; i < 5 - Math.round(info.avgrate); i++) {
            tmp.push(`<i class="bi bi-star"></i>`);
        }
        embedInfo.avg = tmp.join("");

    });

    function changeInput(which) {
        if (which == 1) {
            inputarea.description = window.location.href.toString();
        } else if (which == 2) {
            inputarea.description = `<iframe src="${store.state.EmbedLink}/embed?article=${id}&title=${inputarea.title}&thumbnail=${inputarea.thumbnail}&date=${inputarea.date}&rating=${inputarea.rating}&like=${inputarea.like}&logo=${inputarea.logo}&author=${inputarea.author}" width="${embedInfo.width}" height="${embedInfo.width/2}"></iframe>`
        } else if (which == 3){
            getShareModalImg()
            inputarea.description = '복사되었습니다. CTRL + V로 원하시는 사이트에 붙여넣어보세요!'
        }
    }

    function changeInputRealtime(){
        inputarea.description = `<iframe src="${store.state.EmbedLink}/embed?article=${id}&title=${inputarea.title}&thumbnail=${inputarea.thumbnail}&date=${inputarea.date}&rating=${inputarea.rating}&like=${inputarea.like}&logo=${inputarea.logo}&author=${inputarea.author}" width="${embedInfo.width}" height="${embedInfo.width/2}"></iframe>`
    }

    

</script>
<style scoped lang="sass">
    .modal-btn
        position: relative
        background-color: white
        color: black
        padding: 0.4em 1.2em
        cursor: pointer
        user-select: none
        text-align: center
        text-decoration: none
        cursor: pointer
        transition-duration: 0.4s
        -webkit-transition-duration: 0.4s


    .modal-btn:hover
        transition-duration: 0.1s


    .modal-btn:after
        content: ""
        display: block
        position: absolute
        left: 0
        top: 0
        width: 100%
        height: 100%
        opacity: 0
        transition: all 0.5s
        box-shadow: 0 0 10px 40px white


    .modal-btn:active:after
        box-shadow: 0 0 0 0 white
        position: absolute
        left: 0
        top: 0
        opacity: 1
        transition: 0s


    .modal-btn:active
        top: 1px
        background-color: #ebe8e8


    .content
        display: block
        border: none
        border-bottom: solid 1px black
        -webkit-transition: all 0.5s cubic-bezier(0.64, 0.09, 0.08, 1)
        transition: all 0.5s cubic-bezier(0.64, 0.09, 0.08, 1)
        background: -webkit-linear-gradient(top, rgba(255, 255, 255, 0) 98%, black 10px)
        background: linear-gradient(to bottom, rgba(255, 255, 255, 0) 98%, black 10px)
        background-repeat: no-repeat
        color: black
        resize: none
        overflow: scroll
        background-position: -1140px 0
        background-size: 1140px 100%
        -ms-overflow-style: none
        scrollbar-width: none
        overflow: visible
        &::-webkit-scrollbar
            display: none


    .content:focus,
    .content:valid
        box-shadow: none
        outline: none
        background-position: 0 0

    .md-nav .link,
    .md-nav .embed
        cursor: pointer
    .preview-context-context_description
        img
            width: 10%
</style>
