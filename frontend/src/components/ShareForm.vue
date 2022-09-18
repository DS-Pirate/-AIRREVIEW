<template>
    <div class="preview p-3 my-2 border">
        <div class="preview-title text-center"> <h3> Lorem Ipsum is simply dummy text </h3></div>
        <hr>
        <div class="preview-context d-flex gap-2 align-items-top">
            <div class="preview-context-thumbnail w-30 d-flex justify-content-center">
                <img src="@/assets/aaaa.png" alt="" class="preview-context-thumbnail-img img-fluid">
            </div>
            <div class="preview-context-context w-70">
                <div class="preview-context-context-rating mb-3">★★★★★</div>
                <p class="preview-context-context_description"> Lorem ipsum, dolor sit amet consectetur adipisicing elit. Quas recusandae totam aliquid doloribus numquam ex error illo quia ipsa cupiditate cumque beatae, rem quasi odio quos quis voluptatum tenetur quibusdam. 
                    Lorem ipsum, dolor sit amet consectetur adipisicing elit. Quas recusandae totam aliquid doloribus numquam ex error illo quia ipsa cupiditate cumque beatae, rem quasi odio quos quis voluptatum tenetur quibusdam.
                </p>
            </div>
        </div>
        <hr>
        <div class="preview-footer d-flex justify-content-between px-3">
            <div class="preview-footer-logo">
                <span class="preview-footer-logo_text">에어리뷰</span>
            </div>
            <div class="preview-footer-etcinfo d-flex gap-3">
                <span class="preview-footer-etcinfo_author">해적단 저</span>
                <span class="preview-footer-etcinfo_verticalbar">|</span> 
                <span class="preview-footer-etcinfo_likecount">♥9.9k</span>
                <span class="preview-footer-etcinfo_verticalbar">|</span> 
                <span class="preview-footer-etcinfo_reddate">2022.09.19</span>
            </div>
        </div>
    </div>
</template>
<script setup>
</script>
<style scoped lang="sass">
    .preview-context-context_description
        overflow: hidden
        line-height: 1.2rem
        max-height: 8.4rem
        text-overflow: ellipsis
</style>
