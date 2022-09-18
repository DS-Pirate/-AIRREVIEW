<template>
    <div class="modal" id="share" tabindex="-1" aria-labelledby="share" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content rounded-0">
                <div class="modal-header">
                    <h5 class="modal-title" id="share">글 퍼가기</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body d-flex">
                    <div class="d-flex flex-column justify-content-between w-20">
                        <div class="md-nav"><span>퍼가기</span> | <span>embed</span></div>
                        <br />
                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="flexSwitchCheckChecked" checked />
                            <label class="form-check-label" for="flexSwitchCheckChecked">제목</label>
                        </div>
                        <br />

                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="flexSwitchCheckChecked" checked />
                            <label class="form-check-label" for="flexSwitchCheckChecked">썸네일</label>
                        </div>
                        <br />

                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="flexSwitchCheckChecked" checked />
                            <label class="form-check-label" for="flexSwitchCheckChecked">날짜</label>
                        </div>
                        <br />

                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="flexSwitchCheckChecked" checked />
                            <label class="form-check-label" for="flexSwitchCheckChecked">평점</label>
                        </div>
                        <br />

                        <div class="form-check form-switch">
                            <input class="form-check-input rounded-3" type="checkbox" role="switch" id="flexSwitchCheckChecked" checked />
                            <label class="form-check-label" for="flexSwitchCheckChecked">좋아요</label>
                        </div>
                        <br />
                    </div>

                    <div class="preview">
                        <form>
                            <textarea class="content" required></textarea>
                        </form>
                        <div class="sharePreview"><ShareForm /></div>
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
    import ShareForm from "@/components/ShareForm.vue";



</script>
<style scoped>
    .modal-btn {
        position: relative;
        background-color: white;
        color: black;
        padding: 0.4em 1.2em;
        cursor: pointer;
        user-select: none;
        text-align: center;
        text-decoration: none;
        cursor: pointer;
        transition-duration: 0.4s;
        -webkit-transition-duration: 0.4s;
        /* Safari */
    }

    .modal-btn:hover {
        transition-duration: 0.1s;
    }

    .modal-btn:after {
        content: "";
        display: block;
        position: absolute;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        opacity: 0;
        transition: all 0.5s;
        box-shadow: 0 0 10px 40px white;
    }

    .modal-btn:active:after {
        box-shadow: 0 0 0 0 white;
        position: absolute;
        left: 0;
        top: 0;
        opacity: 1;
        transition: 0s;
    }

    .modal-btn:active {
        top: 1px;
        background-color: #ebe8e8;
    }

    .modal-content {
        min-width: 900px;
        transform: translateX(-30vh);
        min-height: 80vh !important;
    }

    .content {
        font-family: Segoe UI;
        display: block;
        border: none;
        border-bottom: solid 1px black;
        -webkit-transition: all 0.5s cubic-bezier(0.64, 0.09, 0.08, 1);
        transition: all 0.5s cubic-bezier(0.64, 0.09, 0.08, 1);
        background: -webkit-linear-gradient(top, rgba(255, 255, 255, 0) 98%, black 10px);
        background: linear-gradient(to bottom, rgba(255, 255, 255, 0) 98%, black 10px);
        background-repeat: no-repeat;
        color: black;
        resize: none;
        overflow: hidden;
        width: 680px;
        height: 120px;
        background-position: -690px 0;
        background-size: 680 100%;
    }

    .content:focus,
    .content:valid {
        box-shadow: none;
        outline: none;
        background-position: 0 0;
    }

    .sharePreview {
        padding-top: 20px;
    }
</style>
