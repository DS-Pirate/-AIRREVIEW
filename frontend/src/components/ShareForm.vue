<template>
  <div class="shareForm">
    <div class="title"><span class="a">"Lorem Ipsum is simply dummy text"
        <hr>
      </span></div>

    <div class="content">
      <div class="image">
        <img src="../assets/aaaa.png" class="img">
      </div>
      <div class="article">
        <div class="star">★★★☆☆</div>
        <span>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the
          industry's standard dummy text ever since </span>
      </div>
    </div>

    <hr style="margin: 0;">
    <div class="footer">
      <div class="logo">AirReview</div>
      <div class="footerRight">
        <div class="writer">해적단 저 </div>
        <div class="verticalBar"> | </div>
        <div class="likes">♡7.5k </div>
        <div class="verticalBar"> | </div>
        <div class="date">2022.08.13 </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
}
</script>
<style scoped>
  .shareForm{
      width: 440px;
      height: 280px;
      border: 1px solid black;
  }
  .shareForm .title{
      padding-top: 1%;
      width: 100%;
      height: 18%;
      font-size: 22px;
      font-weight: bolder;
      font-style: italic;
      display: flex;
      justify-content: center;
  }
  .shareForm .content{
      margin: 0 auto;
      width: 100%;
      height: 67%;
      display: flex;
  }
  .shareForm .image{
      margin-left: 3%;
      height: 100%;
      width: 42%;
      min-width: 42%;
      
  }
  .shareForm .img{
      max-height: 100%;
      max-width: 100%;
      width: auto;
      height: auto;
  }
  .shareForm .article{
      padding: 0 3% 0 3%;
      height: 100%;
  }
  .shareForm .star{
      margin-bottom: 3%;
      font-size: 20px;
      font-weight: bolder;
  }
  .shareForm .footer{
      /* background-color: green; */
      width: 100%;
      height: 15%;
      margin: 1%;
      font-size: 15px;
  }
  .shareForm .logo{
      padding-left: 3%;
      float: left;
      font-weight: bold;
  }
  .shareForm .footerRight{
      color: rgb(61, 61, 61);
      padding-right: 3%;
      display: flex;
      float: right;
  }
  .shareForm .verticalBar{
      margin: 0 8px 0 8px;
  }
</style>