<template>
  <div class="searchlist">
  <div class="">
    <!-- 검색필터 -->
    <div class="btn-group" role="group" aria-label="Default button group">
      <h2>최근에 조회한 글</h2>
    </div>

    <!-- 검색카드 -->
    <div class="row row-cols-1 my-5">
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