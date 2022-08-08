<template>

  <form class="d-flex flex-column" action="service" method="post">
      <ul class="nav nav-tabs pt-5" id="myTab" role="tablist">
  <li class="nav-item" role="presentation">
    <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">문의</button>
  </li>
  <li class="nav-item" role="presentation">
    <button class="nav-link" id="contact-tab" data-bs-toggle="tab" data-bs-target="#contact-tab-pane" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">기타</button>
  </li>
</ul>


<div class="accordion accordion-flush" id="accordionFlushExample">
  <div class="accordion-item">
    <h2 class="accordion-header" id="flush-headingOne">
      <button class="accordion-button collapsed py-4 px-3 fw-bold" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
        질문 #1
      </button>
    </h2>
    <div id="flush-collapseOne" class="accordion-collapse collapse bg-light border" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
      <div class="accordion-body pt-3">모달창이 이상하게 떠서 일단 여기까지만 해놨음..ㅠㅠ
        <div class="accordion-body d-flex flex-row-reverse pb-1"><code>답변완료</code></div>
      </div>
    </div>
  </div>

  <div class="accordion-item">
    <h2 class="accordion-header" id="flush-headingTwo">
      <button class="accordion-button collapsed py-4 px-3 fw-bold" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseOne">
        질문 #2
      </button>
    </h2>
    <div id="flush-collapseTwo" class="accordion-collapse collapse bg-light border" aria-labelledby="flush-headingTwo" data-bs-parent="#accordionFlushExample"> 
      <div class="accordion-body pt-3"><code>지영누나</code>
      <div class="accordion-body d-flex flex-row-reverse pb-1"><span>답변대기</span></div>
      </div>
    </div>
  </div>
  
  <div class="accordion-item">
    <h2 class="accordion-header" id="flush-headingThree">
      <button class="accordion-button collapsed py-4 px-3 fw-bold" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseOne">
        질문 #3
      </button>
    </h2>
    <div id="flush-collapseThree" class="accordion-collapse collapse bg-light border" aria-labelledby="flush-headingThree" data-bs-parent="#accordionFlushExample">
      <div class="accordion-body pt-3">Placeholder content for this accordion, which is intended to demonstrate the <code>.accordion-flush</code> class. This is the third item's accordion body.
      <div class="accordion-body d-flex flex-row-reverse pb-1"><span>답변대기</span></div>
    </div>
  </div>
  </div>
</div>
  </form>
  <br><nav aria-label="Page navigation example" class="d-flex align-item-center justify-content-center">
  <ul class="pagination">
    <li class="page-item ">
      <a class="page-link" href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li class="page-item"><a class="page-link" href="#">1</a></li>
    <li class="page-item"><a class="page-link" href="#">2</a></li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
</template>
<script>
export default {

}
</script>
<style lang="">
    
</style>