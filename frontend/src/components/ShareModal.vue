<template>
    <div class="modal" id="share" tabindex="-1" aria-labelledby="share" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-xl">
            <div class="modal-content rounded-0">
                <div class="modal-header">
                    <h5 class="modal-title" id="share">글 퍼가기</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="md-nav text-center mt-2 d-flex gap-3 justify-content-center">
                    <span class="link">링크로 퍼가기</span>
                    <span>|</span>
                    <span class="embed">embed</span>
                </div>
                <hr class="my-2" style="opacity: 0.2" />
                <div class="modal-body pt-0 d-flex w-100">
                    <div class="d-flex flex-column justify-content-between w-15">
                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="title" checked />
                            <label class="form-check-label" for="title">제목</label>
                        </div>

                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="thumbnail" checked />
                            <label class="form-check-label" for="thumbnail">썸네일</label>
                        </div>

                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="regDate" checked />
                            <label class="form-check-label" for="regDate">날짜</label>
                        </div>

                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="rate" checked />
                            <label class="form-check-label" for="rate">별점</label>
                        </div>

                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="like" checked />
                            <label class="form-check-label" for="like">좋아요</label>
                        </div>
                    </div>

                    <div class="preview w-100">
                        <form>
                            <textarea class="content w-100" readonly></textarea>
                        </form>
                        <div class="sharePreview">
                            <ShareForm class="w-100 my-2" />
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
    import ShareForm from "@/components/ShareForm.vue"
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
