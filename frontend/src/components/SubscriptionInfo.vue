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
                <div class="detail mb-1">
                    <div class="row align-items-start">
                        <div class="col">
                            <span class="detailWriting">Posts</span>
                            <br>
                            <span class="detailNumbers">1.4k</span>
                        </div>
                        <div class="col">
                            <span class="detailWriting">Followers</span>
                            <br>
                            <span class="detailNumbers">235</span>
                        </div>
                        <div class="col">
                            <span class="detailWriting">Following</span>
                            <br>
                            <span class="detailNumbers">33</span>
                        </div>
                    </div>
                </div>
                <div class="button-contener">
                    <a href="#" class="button unfollow" v-if="following == true" @click="following = false">Unfollow</a>
                    <a href="#" class="button followBtn" v-if="following == false" @click="following = true">Follow</a>
                    <a href="#" class="button readMore">Read More</a>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'SubscriptionInfo',
    data() {
        return {
            following: true,
        }
    }
};
</script>

<style scoped lang="scss">
</style>
