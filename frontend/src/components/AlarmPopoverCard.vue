<template>
    <div class="popovercard px-2">
        <div class="popovercard-profilesection d-flex py-3">
            <div class="popovercard-profilesection__left w-15 d-flex align-items-center">
                <img class="img-fluid p-2" src="@/assets/pngwing.com.png" alt="" srcset="">
            </div>
            <div class="popovercard-profilesection__right w-85 d-flex gap-1">
                <div class="popovercard-profilesection__right__context w-80">
                    <div class="popovercard-profilesection__right__context__user">ㅁㅁ가 ㅁㅁ에게 댓글을 남겼습니다</div>
                    <div class="popovercard-profilesection__right__context__context">"화이팅해봅시다"</div>
                    <div class="popovercard-profilesection__right__context__title">::에어리뷰 작성해보자</div>
                </div>
                <div class="popovercard-profilesection__right__date w-20 px-1 d-flex align-items-center"><span>3일 전</span></div>
            </div>
        </div>
    </div>
</template>
<script setup>

</script>
<style scoped lang="sass">
    .popovercard-profilesection__right__date
        font-size: 0.85rem
        white-space: nowrap
    .popovercard-profilesection__right__context__context
        color: #bbb
    .popovercard-profilesection__right__context__title
        color: #bbb
</style>
