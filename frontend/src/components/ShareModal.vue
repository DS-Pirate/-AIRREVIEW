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
                </div>
                <hr class="my-2" style="opacity: 0.2" />
                <div class="modal-body pt-0 d-flex w-100">
                    <div class="d-flex flex-column justify-content-between w-15">
                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="title" :checked="inputarea.title" @click="inputarea.title = !inputarea.title"/>
                            <label class="form-check-label" for="title">제목</label>
                        </div>

                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="thumbnail" :checked="inputarea.thumbnail" @click="inputarea.thumbnail = !inputarea.thumbnail"/>
                            <label class="form-check-label" for="thumbnail">썸네일</label>
                        </div>

                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="author" :checked="inputarea.author" @click="inputarea.author = !inputarea.author"/>
                            <label class="form-check-label" for="author">글쓴이</label>
                        </div>

                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="regDate" :checked="inputarea.date" @click="inputarea.date = !inputarea.date"/>
                            <label class="form-check-label" for="regDate">날짜</label>
                        </div>

                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="rate" :checked="inputarea.rating" @click="inputarea.rating = !inputarea.rating"/>
                            <label class="form-check-label" for="rate">별점</label>
                        </div>

                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="like" :checked="inputarea.like" @click="inputarea.like = !inputarea.like"/>
                            <label class="form-check-label" for="like">좋아요</label>
                        </div>

                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="logo" :checked="inputarea.logo" @click="inputarea.logo = !inputarea.logo"/>
                            <label class="form-check-label" for="logo">로고</label>
                        </div>
                    </div>

                    <div class="preview w-100" ref="previ">
                        <form>
                            <textarea class="content w-100" readonly v-model="inputarea.description"></textarea>
                        </form>
                        <div class="sharePreview">
                            <div class="preview p-3 my-2 border">
                                <div class="preview-title text-center" v-show="inputarea.title"><h3>Lorem Ipsum is simply dummy text</h3></div>
                                <hr />
                                <div class="preview-context d-flex gap-2 align-items-top">
                                    <img src="@/assets/aaaa.png" alt="image" class="w-30 preview-context-thumbnailimg img-fluid" v-show="inputarea.thumbnail"/>
                                    <div class="preview-context-context w-70 d-flex flex-column gap-3">
                                        <span class="preview-context-context-rating" v-show="inputarea.rating">★★★★★</span>
                                        <p class="preview-context-context_description">
                                            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Quas recusandae totam aliquid doloribus numquam ex error illo quia ipsa cupiditate cumque beatae, 
                                            rem quasi odio quos quis voluptatum tenetur quibusdam. 
                                            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Quas recusandae totam aliquid doloribus numquam ex error illo quia ipsa cupiditate cumque beatae, 
                                            rem quasi odio quos quis voluptatum tenetur quibusdam.</p>
                                    </div>
                                </div>
                                <hr />
                                <div class="preview-footer d-flex justify-content-between px-3">
                                    <div class="preview-footer-logo">
                                        <span v-show="inputarea.logo" class="preview-footer-logo_text">에어리뷰</span>
                                    </div>
                                    <div class="preview-footer-etcinfo d-flex gap-3">
                                        <span v-show="inputarea.author" class="preview-footer-etcinfo_author">해적단 저</span>
                                        <span v-show="inputarea.author" class="preview-footer-etcinfo_verticalbar">|</span>
                                        <span v-show="inputarea.like" class="preview-footer-etcinfo_likecount">♥9.9k</span>
                                        <span v-show="inputarea.like" class="preview-footer-etcinfo_verticalbar">|</span>
                                        <span v-show="inputarea.date" class="preview-footer-etcinfo_reddate">2022.09.19</span>
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
    import { reactive, ref } from "vue";

    let inputarea = reactive({
        description: "",
        title: true,
        thumbnail: true,
        date: true,
        rating: true,
        like: true,
        logo: true,
        author: true
    });


    function changeInput(which) {
        if (which == 1) {
            inputarea.description = window.location.href.toString();
        } else if (which == 2) {
            inputarea.description = "";
        }
        console.log(inputarea);
    }
    let previ = ref(null)
    console.log(previ);
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
        /* Safari */


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
        overflow: hidden
        background-position: -1140px 0
        background-size: 1140px 100%


    .content:focus,
    .content:valid
        box-shadow: none
        outline: none
        background-position: 0 0

    .md-nav .link,
    .md-nav .embed
        cursor: pointer
</style>
