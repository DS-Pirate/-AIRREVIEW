<template>
    <div class="channel">
        <div class="metadata">
            <img ref="IuserId" src="@/assets/pngwing.com.png" alt="" />
            <div class="info">
                <span class="name" ref="IairName">동성리뷰단</span>
                <span class="subscribers">구독자 1명</span>
            </div>
        </div>
        <button class="btn btn-outline-primary">구독</button>
    </div>
</template>
<script setup>
    import { defineProps } from "vue";
    const props = defineProps(["id"])
    props
</script>
