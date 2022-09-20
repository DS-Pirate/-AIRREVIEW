<template>
    <ul class="flex-column m-0 p-0 subscribelist">
      <li class="nav-item d-flex m-0 p-0">
          <a href="#" class="nav-link  d-flex justify-content-center align-items-left" aria-current="page">
            <img src="@/assets/pngwing.com.png" class="bi me-2" width="25" height="25"/>
            <span>{{props.subsinfo.airName}}</span>
          </a>
      </li>
  </ul>
</template>
<script setup>
  import { defineProps } from 'vue';

  let props = defineProps(["subsinfo"])
  console.log(props.subsinfo);
</script>
<style lang="">
    
</style>