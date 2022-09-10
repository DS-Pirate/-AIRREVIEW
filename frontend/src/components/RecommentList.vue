<template>
    <section class="recommend w-15">
      <span class="title">추천 게시글</span>
      <div class="row-cols-1  py-4">
        <Cards v-for="i in 32" :key="i" class="col">
          Column
        </Cards>
      </div>
    </section>
</template>
<script setup>
    import Cards from './MainCards.vue';
    

</script>
