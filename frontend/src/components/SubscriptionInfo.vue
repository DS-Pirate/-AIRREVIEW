<template>
     <div class="subInfo">
  <div class="index-post">
        <div v-for="contener in 20" :key="contener" class="contener">
            <div class="image-contener">
                <img src="../assets/pngwing.com.png" alt="">
            </div>
            <div class="meta-contener">
                <h2 class="title">Jiyoung</h2>
                <span class="desc">간식 전용 리뷰어입니다. 주로...</span>
            </div>
            <div class="button-contener">
                <a href="#" class="button">Read More →</a>
            </div>
        </div>
    </div>
   </div>
</template>

<script>
export default {};
</script>

<style scoped lang="scss">

</style>
