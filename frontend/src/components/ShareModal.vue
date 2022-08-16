<template>
  <div class="modal " id="share" tabindex="-1" aria-labelledby="share" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content rounded-0">
        <div class="modal-header">
          <h5 class="modal-title" id="share">글 퍼가기</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        
        <div class="modal-body d-flex">
            <div class="bg-primary w-30">
              <div class="md-nav"><span>퍼가기</span> | <span>embaded</span></div>
              <div class="radios">a<br>b<br></div>

            </div>
            <div class="bg-warning w-70">
              <div class="h-50">textarea</div>
              <div class="h-50">preview</div>

            </div>

        </div>
        <div class="modal-footer">
          <button type="button" class="modal-btn border-0 m-0">복사</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {

}
</script>
<style>
.modal-btn {
  position: relative;
  background-color: white;
  color: black;
  padding: 0.4em 1.2em;
  cursor: pointer;
  user-select: none;
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  transition-duration: 0.4s;
  -webkit-transition-duration: 0.4s;
  /* Safari */
}

.modal-btn:hover {
  transition-duration: 0.1s;
}

.modal-btn:after {
  content: "";
  display: block;
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
  transition: all 0.5s;
  box-shadow: 0 0 10px 40px white;
}

.modal-btn:active:after {
  box-shadow: 0 0 0 0 white;
  position: absolute;
  left: 0;
  top: 0;
  opacity: 1;
  transition: 0s;
}

.modal-btn:active {
  top: 1px;
  background-color: #ebe8e8;
}


.modal-content{
  min-height: 50vh;
  min-width: 720px;
  transform: translateX(-15vh);
}
</style>