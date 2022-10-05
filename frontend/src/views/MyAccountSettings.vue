<template>
  <div class="MyAccountSettings">

    <div class="settingpage">
      <div class="myaccount section py-5 px-3" id="myaccount">
        <MyAccount/>
      </div>

      <div class="articles section py-5 px-3" id="articles">
        <PostManagement/>
      </div>

      <div class="report section py-5 px-3" id="report">
        <ReportList/>
      </div>

      <div class="qa section py-5 px-3" id="qa">
        <QnA/>
      </div>
    </div>
    <div class="subnav mb-5 mt-4 border-bottom d-flex justify-content-end sticky-bottom">
      <hr>
      <ul class="list-group list-group-horizontal w-100 justify-content-between">
        <li class="list-group-item sticky-bottom border-0"><a href="#myaccount">My Account</a></li>
        <li class="list-group-item sticky-bottom border-0"><a href="#articles">Article List</a></li>
        <li class="list-group-item sticky-bottom border-0"><a href="#report">Report List</a></li>
        <li class="list-group-item sticky-bottom border-0"><a href="#qa">Q&A List</a></li>
      </ul>
    </div>

  </div>
</template>

<script>
import ReportList from '@/components/ReportList.vue'
import QnA from '@/components/QnA.vue'
import MyAccount from '@/components/MyAccount.vue'
import PostManagement from '@/components/PostManagement.vue';

export default {
  name: 'MyAccountSettings',
  components: {
    ReportList,
    QnA,
    MyAccount,
    PostManagement

}
}
</script>

<style lang="sass" scoped>
  .section
    height: 97.5vh
  .subnav
      background-color: white
      ul
        a
          color: black
          text-decoration: none
</style>
