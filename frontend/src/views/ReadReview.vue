<template>
    <div class="readReview">
      <!-- 게시글 정보 -->
      <section class="info">
        <div class="metadata">

        <!-- 제목 -->
        <div  class="title" >
            <h1>
            제목
            </h1>
        </div>
          <hr>

        <div class="hashAndStar">
        <!-- 해시태그 -->
          <ul class="hash">
            <li>#개발</li>
            <li>#프론트</li>
            <li>#백엔드</li>
            <li>#에어리뷰</li>
          </ul>
          
        <!-- 별점  -->
        <form name="myform" id="myform" method="post" action="./save">
            <fieldset>
                <input type="radio" name="rating" value="5" id="rate1"><label for="rate1">⭐</label>
                <input type="radio" name="rating" value="4" id="rate2"><label for="rate2">⭐</label>
                <input type="radio" name="rating" value="3" id="rate3"><label for="rate3">⭐</label>
                <input type="radio" name="rating" value="2" id="rate4"><label for="rate4">⭐</label>
                <input type="radio" name="rating" value="1" id="rate5"><label for="rate5">⭐</label>
            </fieldset>
        </form>
        </div>
        <hr>    

          
          <!-- 내용 -->
          <div class="contentAndButton">
            <p class="reviewContent clamp">
              ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ
              <br>
              ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ

            </p>
          </div>

          <!-- 게시글 쓴 날짜 -->
          <span class="views">1시간 전</span>
        </div>

        <hr>
        <!-- 버튼 -->
        <ul class="actions">
          <li>
            <button><span>Like</span>
            </button>
          </li>
          <li>
            <button><span>Share</span></button>
          </li>
          <li>
            <button><span>Save</span></button>
          </li>
          <li>
            <button><span>Report</span>
            </button>
          </li>
        </ul>


        <!-- 채널 구독 등 -->
        <div class="channel">
          <div class="metadata">
            <img src="@/assets/pngwing.com.png" alt="" />
            <div class="info">
              <span class="name">동성리뷰단</span>
              <span class="subscribers">구독자 1명</span>
            </div>
          </div>
          <button class="btn btn-outline-primary">구독</button>
        </div>

            <!-- 댓글 -->
        <ReadComment/>
      </section>









      <!-- 추천 게시글 -->
      <section class="recommend">
        <span class="title">추천 게시글</span>

         <div class="row-cols-1 abc">
            <Cards v-for="i in 32" :key="i" class="col">
            Column
            </Cards>
        </div>
      </section>
    </div>

</template>

<script>
// @ is an alias to /src
import Cards from '@/components/MainCards.vue'
import ReadComment from '@/components/ReadComment.vue'
export default {
  name: 'ReadReview',
  components: {
    ReadComment,
    Cards
  }
}
</script>

<style>
</style>