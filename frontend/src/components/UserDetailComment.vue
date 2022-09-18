<template>

<div class="modal fade" :id="'UserDetail'+props.userDetail.cid" tabindex="-1" aria-labelledby="UserDetailLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="UserDetailLabel">{{props.userDetail.username}}</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        {{userDetail}}
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
</template>
<script setup>
import { defineProps } from 'vue';
let props = defineProps(["userDetail"])
console.log(props.userDetail);

</script>
<style lang="sass">
    
</style>