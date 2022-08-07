<template>
  <div class="mainviewpage">


  <div class="row row-cols-3">

    <Cards v-for="i in 32" :key="i" class="col">
      Column
    </Cards>

  </div>

  </div>
</template>

<script>
// @ is an alias to /src

import Cards from '@/components/MainCards.vue'

export default {
  name: 'HomeView',
  components: {

    Cards
  }
}
</script>
