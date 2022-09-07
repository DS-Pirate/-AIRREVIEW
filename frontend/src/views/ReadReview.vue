<template>
  <div class="readReview justify-content-between">
    <!-- 게시글 정보 -->
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
            <div class="ating-stars-section_accuraterating d-flex justify-content-between"
              style="font-size:0.1rem; color: #aaa;">
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
      <ul class="actions mx-0 px-1">
        <li>
          <button class="border-0 bg-white" data-bs-toggle="modal" data-bs-target="#share"><span>Share</span></button>
        </li>
        <li v-if="functionBtnChecking()">
          <button class="border-0 bg-white" @click="addToLike"><span ref="fav">Like</span></button>
        </li>
        <li v-if="functionBtnChecking()">
          <button class="border-0 bg-white" @click="addSave"><span ref="save">Save</span></button>
        </li>
        <li v-if="functionBtnChecking()">
          <button class="border-0 bg-white" data-bs-toggle="modal" data-bs-target="#ReportModal"><span ref="report">Report</span></button>
        </li>
      </ul>

      <ArticleUserCard :id="id"></ArticleUserCard>
      <ArticleCommentList></ArticleCommentList>
      <ReportModal :id="id"></ReportModal>



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
import ReportModal from '../components/ReportModal.vue'
import ArticleUserCard from '@/components/ArticleUserCard.vue'
export default {
  name: 'ReadReview',
  components: {
    Cards,
    ArticleCommentList,
    ReportModal,
    ArticleUserCard
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
    let userCardInfo = []
    let thisstorevariationshouldbeimplementedtostoresuseridkey = 1
    let articleInfo = null
    let userInfo = null
    let id = new URLSearchParams(window.location.search).get('article');
    let fav = ref(null)
    let report = ref(null)
    let save = ref(null)


    function functionBtnChecking() {
      if (thisstorevariationshouldbeimplementedtostoresuseridkey != 0) {
        return true
      } else {
        return false
      }
    }

    if (id.length == 0) {
      errorAndGetBack()
    }

    function getArticleFunctionStatus() {
      const url = "./api/article/functions"
      const headers = {
        "Content-Type": "application/json; charset=utf-8",
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjIwMDY2NjUsImV4cCI6MTY2NDU5ODY2NSwic3ViIjoiMWFhYUBhYWEuY29tIn0.SLdsL0VW2nyHEwkrAAqqn6uvUmpqMSHbUg81530SQvA",
      }
      let body = {
        //store에서 userid획득
        userid: 1,
        aid: id
      }

      axios.post(url, body, {headers})
      .then(function(res){
        if(res.data.favo == true){
          fav.value.style.color = "#3041a9"
        }
        if(res.data.save == true){
          save.value.style.color = "#3041a9"
        }
        if(res.data.report == true){
          report.value.style.color = "red"
          report.value.textContent = "Reported"
        }

      })
      .catch((e)=>{
        console.log(e);
      })

    }



    async function addToLike() {
      const url = "./api/article/like"
      const headers = {
        "Content-Type": "application/json; charset=utf-8",
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjIwMDY2NjUsImV4cCI6MTY2NDU5ODY2NSwic3ViIjoiMWFhYUBhYWEuY29tIn0.SLdsL0VW2nyHEwkrAAqqn6uvUmpqMSHbUg81530SQvA",
      }
      let body = {
        //store에서 userid획득
        userid: 1,
        aid: id
      }
      await axios.post(url, body, { headers })
        .then(res => alert(res.data))
        .catch(e => console.log(e))
      router.go(0)
    }
    async function addSave() {
      const url = "./api/article/save"
      const headers = {
        "Content-Type": "application/json; charset=utf-8",
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjIwMDY2NjUsImV4cCI6MTY2NDU5ODY2NSwic3ViIjoiMWFhYUBhYWEuY29tIn0.SLdsL0VW2nyHEwkrAAqqn6uvUmpqMSHbUg81530SQvA",
      }
      let body = {
        //store에서 userid획득
        userid: 1,
        aid: id
      }
      await axios.post(url, body, { headers })
        .then(res => alert(res.data))
        .catch(e => console.log(e))
      router.go(0)
    }


    function errorAndGetBack() {
      alert("잘못된 접근입니다")
      router.push("/")
    }

    function getTimeFromJavaDate(s) {
      const cont = new Date(s)
      let date = new Date()
      let calculated = (new Date(date.getTime()) - cont) / 1000 //초 계산
      if (calculated < 60) {
        return "방금 전"
      } else if (calculated < 60 * 60) {
        return `${Math.round(calculated / (60))}분 전`
      } else if (calculated < 60 * 60 * 24) {
        return `${Math.round(calculated / (60 * 60))}시간 전`
      } else if (calculated < 60 * 60 * 24 * 7) {
        return `${Math.round(calculated / (60 * 60 * 24))}일 전`
      } else if (calculated < 60 * 60 * 24 * 7 * 5) {
        return `${Math.round(calculated / (60 * 60 * 24 * 7))}주 전`
      } else if (calculated > 31536000) {
        return `${Math.round(calculated / 31536000)}년 전`
      }
    }


    async function getArticleInformation() {
      await axios.get(`/airreview/article/read/${id}`)
        .then(res => {
          articleInfo = res.data.articleInfo
          Iatitle.value.innerText = articleInfo.atitle
          Icontext.value.innerHTML = articleInfo.context
          Iregdate.value.innerText = getTimeFromJavaDate(articleInfo.regdate)
          ratingnum.value.innerText = (res.data.articleAVG != undefined) ? res.data.articleAVG.toFixed(2) : 0
          let tmp = []
          for (let element of articleInfo.tags) {
            tmp.push(`<span>#${element}<span>`)
          }
          Itags.value.innerHTML = tmp.join(" ")
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

    getArticleInformation()
    getArticleFunctionStatus()
    return { articleInfo, userInfo, commentInfo, id, Iatitle, Icontext, Ishareable, Itags, IuserId, IairName, Iregdate, stars, ratingnum, getTimeFromJavaDate, userCardInfo, addToLike, addSave, functionBtnChecking,getArticleFunctionStatus, fav, report, save }
  }
}
</script>