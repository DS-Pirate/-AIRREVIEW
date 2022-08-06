<template>
        <writeeditor class="w-100">
            <form action="post">
                <input type="text" placeholder="Title" class="form-control my-3 rounded-0 title" name="writeTitle" id="Title">
                <ckeditor :editor="editor" v-model="editorData" :config="editorConfig"></ckeditor>

            </form>
        </writeeditor>

</template>

<script>

    //composition api style is not supported
    // refer below link https://github.com/ckeditor/ckeditor5-vue/issues/172
    import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
    export default {
        name: 'app',
        data() {
            return {
                editor: ClassicEditor,
                editorData: '<p>내용</p>',
                editorConfig: {
                    language: 'ko'
                }
            };
        }
    }
</script>