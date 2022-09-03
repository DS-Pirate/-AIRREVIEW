<template>
  <div class="readReview justify-content-between">
    <!-- 게시즐 정보 -->
    <section class="info w-100">
      <div class="metadata">

        <!-- 제목 -->
        <div class="title">
          <h1 ref="Iatitle">
          </h1>
        </div>
        <hr>

        <div class="hashAndStar d-flex justify-content-between">
          <!-- 해시태그 -->
          <ul class="hash m-0 p-0 px-1 d-flex align-items-center" ref="Itags">
          </ul>
          <!-- 별점  -->
          <div class="rating-stars-section">
            <div ref="stars"></div>
            <div class="ating-stars-section_accuraterating d-flex justify-content-between" style="font-size:0.1rem; color: #aaa;">
              <span>현재평점</span>
              <span class="rating-stars-section_accuraterating_num" ref="ratingnum"></span>
            </div>
          </div>
        </div>
        <hr>


        <!-- 내용 -->
        <div class="contentAndButton">
          <div class="reviewContent clamp" ref="Icontext">
          </div>
        </div>

        <!-- 게시글 쓴 날짜 -->
        <span class="views" ref="Iregdate">1시간 전</span>
      </div>

      <hr>
      <!-- 버튼 -->
      <ul class="actions">
        <li>
          <button class="border-0 bg-white"><span>Like</span>
          </button>
        </li>
        <li>
          <button class="border-0 bg-white" data-bs-toggle="modal" data-bs-target="#share"><span>Share</span></button>
        </li>
        <li>
          <button class="border-0 bg-white"><span>Save</span></button>
        </li>
        <li>
          <button class="border-0 bg-white"><span>Report</span>
          </button>
        </li>
      </ul>


      <!-- 채널 구독 등 -->
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

      <!-- add Comment -->
      <ArticleCommentList></ArticleCommentList>




    </section>
    <!-- 추천 게시글 -->
    <section class="recommend w-15">
      <span class="title">추천 게시글</span>

      <div class="row-cols-1  py-4">
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
import axios from 'axios'
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import ArticleCommentList from '@/components/ArticleCommentList.vue'
export default {
  name: 'ReadReview',
  components: {
    Cards,
    ArticleCommentList
  },
  setup() {
    const router = useRouter()
    const Iatitle = ref(null)
    const Icontext = ref(null)
    const Ishareable = ref(null)
    const Itags = ref(null)
    const IuserId = ref(null)
    const IairName = ref(null)
    const Iregdate = ref(null)
    let stars = ref(null)
    let ratingnum = ref(null)
    let commentInfo = ""
    /** res.data.articleInfo
     * aid
     * atitle
     * cgroup
     * context
     * shareable
     * tags
     * userId
    */
    let articleInfo = null

    /** res.data.userInfo
     * airName
     * userIntro
     * imgSrc(예정)
     */
    let userInfo = null




    let id = new URLSearchParams(window.location.search).get('article');

    if (id.length == 0) {
      errorAndGetBack()
    }



    function errorAndGetBack() {
      alert("잘못된 접근입니다")
      router.push("/")
    }

    function getTimeFromJavaDate(s) {
            const tTime = s.split(/\D+/);
            const cont = new Date(Date.UTC(tTime[0], --tTime[1], tTime[2], tTime[3], tTime[4], tTime[5], tTime[6])-(9 * 60 * 60 * 1000)-((60*9*1000)+10))
            let calculated = (new Date() - cont)/1000 //초 계산
            if(calculated<60){
                return "방금 전"
            }else if(calculated<60*60){
                return`${Math.round(calculated/(60))}분 전`
            }else if(calculated<60*60*24){
                return`${Math.round(calculated/(60*60))}시간 전`
            }else if(calculated<60*60*24*7){
                return`${Math.round(calculated/(60*60*24))}일 전`
            }else if(calculated<60*60*24*7*5){
                return`${Math.round(calculated/(60*60*24*7))}주 전`
            }else if(calculated>31536000){
                return`${Math.round(calculated/31536000)}년 전`
            }
        }


    async function getArticleInformation() {
      await axios.get(`/airreview/article/read/${id}`)
        .then(res => {
          console.log(res);
          articleInfo = res.data.articleInfo
          userInfo = res.data.userInfo
          Iatitle.value.innerText = articleInfo.atitle
          Icontext.value.innerHTML = articleInfo.context
          IairName.value.innerText = userInfo.airName
          Iregdate.value.innerText = getTimeFromJavaDate(articleInfo.regdate)
          ratingnum.value.innerText = res.data.articleAVG.toFixed(2)
          let tmp = []
          for (let element of articleInfo.tags) {
            tmp.push(`<span>#${element}<span>`)
          }
          Itags.value.innerHTML = tmp.join(" ")
          if (!isNaN(articleInfo.userId)) {
            IuserId.value.src = `./images/read/userid/${articleInfo.userId}`
          }
          let tmp2 = []
          for (let i = 0; i < Math.round(res.data.articleAVG); i++) {
            tmp2.push(`<i class="bi bi-star-fill"></i>`)
          }
          for (let i = 0; i < 5 - Math.round(res.data.articleAVG); i++) {
            tmp2.push(`<i class="bi bi-star"></i>`)
          }
          stars.value.innerHTML = tmp2.join("")


        }).catch(
          e => {
            //에러 처리는 더 궁리 필요 
            if (e.name == "AxiosError") {
              errorAndGetBack()
            } else {
              console.log("loadded!");
              console.log(e);
            }
          }
        )
    }
    console.log(stars);
    getArticleInformation()

    return { articleInfo, userInfo, commentInfo, id, Iatitle, Icontext, Ishareable, Itags, IuserId, IairName, Iregdate, stars, ratingnum, getTimeFromJavaDate }
  }
}
</script>