<template>
    <div class="popover__wrapper">
        <i class="bi bi-bell"></i>
        <div class="popover__content py-3">
            <div class="popover__title pb-3 border border-0 border-bottom">새소식</div>
            <div class="popover__context">
                <AlarmPopoverCard class="border border-0 border-bottom"></AlarmPopoverCard>
            </div>
        </div>
    </div>
</template>
<script setup>
    import AlarmPopoverCard from "./AlarmPopoverCard.vue";
</script>
<style scoped lang="sass">
    a
        text-decoration: none
    .popover__title
        text-decoration: none
        color: black
        text-align: center


    .popover__wrapper
        position: relative

    .popover__content
        right:1%
        border-radius: 0.2rem
        opacity: 0
        visibility: hidden
        position: absolute
        background-color: white
        box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.25)
        width: 25rem
        height: 35rem
        transition: all 0.2s cubic-bezier(0.75, -0.02, 0.2, 0.97)


    .popover__content:before
        right:1%
        position: absolute
        z-index: -1
        content: ""
        border-style: solid
        border-width: 0 10px 10px 10px
        border-color: white
        transition-duration: 0.2s
        transition-property: transform

    .popover__wrapper:hover .popover__content
        right:1%
        z-index: 10
        opacity: 1
        visibility: visible
</style>
