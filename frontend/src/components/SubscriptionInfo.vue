<template>
     <div class="subInfo">
  <div class="index-post">
        <div v-for="contener in 20" :key="contener" class="contener">
            <div class="image-contener">
                <img src="../assets/pngwing.com.png" alt="">
            </div>
            <div class="meta-contener">
                <h2 class="title">Nature World.</h2>
                <span class="desc">Nature World News brings out the science geek in every reader, fostering an improved appreciation of our environment...</span>
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
