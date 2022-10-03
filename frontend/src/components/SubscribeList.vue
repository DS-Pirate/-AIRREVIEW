<template>
    <ul class="flex-column p-0 subscribelist"><br>
      <li class="nav-item d-flex m-0 p-0">
          <a :href="'./mypage?channel=' + props.subsinfo.userid" class="nav-link d-flex justify-content-center align-items-left " aria-current="page">
            <img src="@/assets/pngwing.com.png" class="bi me-2" width="25" height="25"/>
            <span>{{props.subsinfo.airName}}</span>
          </a>
      </li>
  </ul>
</template>
<script setup>
  import { defineProps } from 'vue';
  let props = defineProps(["subsinfo"])
</script>
<style lang="">
    
</style>