<template>
  <div class="searchlist">
  <div class="all">
    <!-- 검색필터 -->
    <div class="btn-group" role="group" aria-label="Default button group">
      <button type="button" class="btn btn-outline-dark">조회순</button>
      <button type="button" class="btn btn-outline-dark">좋아요순</button>
      <button type="button" class="btn btn-outline-dark">평점순</button>
      <button type="button" class="btn btn-outline-dark">최신순</button>
    </div>

    <!-- 검색카드 -->
    <div class="row row-cols-1">
      <Cards v-for="i in 32" :key="i" class="col"> Column </Cards>
    </div>
  </div>
  </div>
</template>

<script>
// @ is an alias to /src

import Cards from "@/components/MainCards.vue";

export default {
  name: "SearchList",
  components: {
    Cards,
  },
};
</script>

<style></style>