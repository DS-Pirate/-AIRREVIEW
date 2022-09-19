<template>
    <writeeditor>
        <input type="text" placeholder="제목" class="form-control my-3 rounded-0 title" v-model="title" id="title" />
        <ckeditor @ready="onReady" :editor="editor" v-model="editorData" :config="editorConfig"></ckeditor>
        <input type="text" v-model="tag" v-on:keyup.space="makingTag" placeholder="태그" class="form-control my-3 rounded-0 hashtag" />
        <hashtags class="hashtags d-flex g-4 overflow-auto">
            <div class="tag d-flex justify-content-center align-items-center" v-for="(item, i) in taghistory" :key="item">
                <span class="m-1">#{{ item }}</span>
                <button class="delbtn d-flex justify-content-center align-items-center px-1">
                    <span v-on:click="deleteTag(i)">x</span>
                </button>
            </div>
        </hashtags>
        <div class="tagwarning" ref="warning"></div>
        <button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasBottom" aria-controls="offcanvasBottom">글쓰기</button>

        <div class="offcanvas offcanvas-bottom" tabindex="-1" id="offcanvasBottom" aria-labelledby="offcanvasBottomLabel">
            <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasBottomLabel">수정하기</h5>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <hr />
            <div class="offcanvas-body small">
                <!-- this secton will arranged after devate about article options -->
                <div class="articlestatus">
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox" v-model="openable" role="switch" id="openable" />
                        <label class="form-check-label" for="openable" v-if="openable">공개</label>
                        <label class="form-check-label" for="openable" v-else>비공개</label>
                    </div>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox" v-model="shareable" role="switch" id="shareable" />
                        <label class="form-check-label" for="shareable" v-if="shareable">공유 가능</label>
                        <label class="form-check-label" for="shareable" v-else>공유 불가능</label>
                    </div>
                </div>
            </div>
            <div class="offcanvas-footer small d-flex justify-content-end">
                <button class="btn btn-primary m-3" @click.prevent="submit">수정하기</button>
            </div>
        </div>
    </writeeditor>
</template>

<script>
    // Composition api style is not supported
    // Refer the below link https://github.com/ckeditor/ckeditor5-vue/issues/172
    // ImageUploader is not implemented yet, it needs making fileserver or db for uploading images
    // It scheduled when backend server developed
    // import ClassicEditor from "@ckeditor/ckeditor5-build-classic"
    import router from "@/router";
    import ClassicEditor from "@ckeditor/ckeditor5-custom/build/ckeditor"
    import axios from "axios";
    import store from "@/store";
    export default {
        name: "WritePage",
        data() {
            let title =""
            return {
                aid : null,
                shareable: true,
                openable: true,
                title: title,
                tag: "",
                taghistory: [],
                editor: ClassicEditor,
                editorData : "",
                editorConfig: {
                    language: "ko",
                    simpleUpload: {
                        uploadUrl: "api/article/write/image",
                        withCredentials: true,
                        headers: {
                            "Authorization": store.state.token,
                            "userid" : store.state.userid
                        },
                    },
                },
            };
        },
        mounted() {
            const getInfo= async()=> {
                let title = ""
                let context = ""
                let tags = []
                let aid = 0
                const headers = {
                    "Content-Type": "application/json; charset=utf-8",
                    "Authorization": store.state.token,
                    "userid" : store.state.userid
                };
                let body = {
                    aid: new URLSearchParams(window.location.search).get("article"),
                    userid: store.state.userid,
                };
                await axios.post("./api/article/modify/check", body, { headers }).then(function (res) {
                    console.log(res);
                    context = res.data.context
                    title = res.data.atitle
                    tags = res.data.tags
                    aid = res.data.aid
                });
                this.editorData = context
                this.title = title
                this.aid = aid
                for (const i in tags) {
                    this.taghistory.push(tags[i])    
                }
                
            }
            getInfo()
        },
        methods: {
            onReady: function onReady(editor) {
                editor.ui.getEditableElement().parentElement.insertBefore(editor.ui.view.toolbar.element, editor.ui.getEditableElement());
            },
            makingTag: function () {
                let result = this.tag.trim().replace(/ /, "");
                if (result.length == 0) {
                    this.$refs.warning.innerText = "공백은 태그로 사용이 불가능합니다";
                    this.tag = "";
                    return;
                }
                if (this.taghistory.includes(result)) {
                    this.$refs.warning.innerText = "이미 등록된 태그입니다";
                    this.tag = "";
                    return;
                }

                this.taghistory.push(result);
                this.tag = "";
            },
            deleteTag: function deltag(i) {
                this.taghistory.splice(i, 1);
            },
            submit: async function () {
                const page = {
                    aid: this.aid,
                    atitle: this.title,
                    context: this.editorData,
                    tags: this.taghistory,
                    opened: this.openable,
                    shareable: this.shareable,
                    userId: store.state.userid,
                    token: store.state.token,
                    images: [],
                };

                //find image name where in context
                function findImageName(list) {
                    let bonary = list.split("/");
                    let filenames = new Array();

                    for (let i in bonary) {
                        if (bonary[i].split("-").length == 5) {
                            let tmp = bonary[i].slice(0, bonary[i].indexOf(">\n<") - 1);
                            if (tmp.split('"')[2] != "YouTube video player") {
                                filenames.push({
                                    fileName: tmp,
                                });
                            }
                        }
                    }
                    return filenames;
                }
                page.images = findImageName(page.context);
                /////////////////////////////////////////
                let result = JSON.stringify(page);
                const url = "/airreview/api/article/modify/send";
                const headers = {
                    "Content-Type": "application/json; charset=utf-8",
                    "Authorization": store.state.token,
                    "userid" : store.state.userid
                };
                const body = result;
                await axios
                    .post(url, body, { headers })
                    .then(function (res) {
                        console.log(res);
                        sessionStorage.setItem("aid", res.data);
                        router.push(`/read?article=${res.data}`);
                    })
                    .catch((e) => {
                        console.log(e + "통신실패");
                    })
                    .then(console.log("통신 끝"));
            },
        },
    };
</script>
