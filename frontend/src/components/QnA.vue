<template>
<h1>Q&A</h1><br>
<div class="accordion accordion-flush" id="accordionFlushExample">
  <div v-for="info, idx in state.questionlist" :key="(info, idx)" class="accordion-item">
    <h2 class="accordion-header" id="flush-headingOne">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" :data-bs-target="'#flush-collapseOne' + idx" aria-expanded="true" aria-controls="flush-collapseOne">
        <h5>{{info.title}}</h5>
      </button>
    </h2>
    <div :id="'flush-collapseOne' + idx" class="accordion-collapse collapse px-4" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
      <div class="accordion-body">{{info.context}}<hr>
        <div><p>{{info.answerContext}}</p></div>
      </div>
    </div>
  </div>
</div>
</template>
<script setup>
import { reactive } from 'vue';
import axios from 'axios';
import store from '@/store';

const state = reactive({
  questionlist: '',
})

const url = store.state.axiosLink+'/api/setting/questionlist'
  const headers = {
    "Content-Type": "application/json",
    "Authorization": store.state.token,
    "userid": store.state.userid
  }

axios.post(url, store.state.userid, { headers }).then(function (res) {
  state.questionlist = res.data;
  console.log(res.data);
})

</script>
<style scoped lang="sass">
  .accordion-collapse
    background-color:gray
</style>
