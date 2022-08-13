<template>
    <div class="modal" id="question" tabindex="-1" aria-labelledby="question" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content  rounded-0">
                <div class="modal-header">
                    <h5 class="modal-title" id="question">문의하기</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="mb-3">
                            <label for="recipient-name" class="col-form-label">문의 제목</label>
                            <input type="text" class="form-control" id="recipient-name" />
                        </div>
                        <div class="mb-3">
                            <label for="message-text" class="col-form-label">문의 내용</label>
                            <textarea class="form-control" id="message-text"></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                    <button type="button" class="btn btn-primary">문의 보내기</button>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default {

}
</script>
