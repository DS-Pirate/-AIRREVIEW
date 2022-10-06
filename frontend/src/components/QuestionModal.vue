<template>
    <div class="modal" id="question" tabindex="-1" aria-labelledby="question" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content rounded-0">
                <div class="modal-header">
                    <h5 class="modal-title" id="question">문의하기</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="mb-3">
                            <label for="recipient-name" class="col-form-label">문의 제목</label>
                            <input type="text" ref="title" class="form-control" id="recipient-name" />
                        </div>
                        <div class="mb-3">
                            <label for="message-text" class="col-form-label">문의 내용</label>
                            <textarea class="form-control" ref="context" id="message-text"></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                    <button type="button" @click="send" class="btn btn-primary">문의 보내기</button>
                </div>
            </div>
        </div>
    </div>
</template>
<script setup>
    import router from "@/router";
    import axios from "axios";
    import { ref } from "vue";
    import { useStore } from "vuex";
    const store = useStore();

    let title = ref(null);
    let context = ref(null);

    async function send() {
        const headers = {
            "Content-Type": "application/json; charset=utf-8",
            Authorization: store.state.token,
            "userid" : store.state.userid
        };

        let body = {
            userid: store.state.userid,
            title: title.value.value,
            context: context.value.value,
        };

        if (title.value.value === "") {
            alert("제목을 적어주세요");
            return false;
        } else if (context.value.value === "") {
            alert("내용을 적어주세요");
            return false;
        }

        await axios
            .post(store.state.axiosLink+"/api/question", body, { headers })
            .then((res) => console.log("신호 나이이스", res))
            .catch((e) => console.log(e))
            alert("문의를 등록했습니다")
            router.go(0)
    }
</script>
