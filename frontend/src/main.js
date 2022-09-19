import { createApp } from 'vue'
import App from '@/App.vue'
import router from '@/router'
import store from '@/store'
import vue3GoogleLogin from 'vue3-google-login'

import { createMetaManager } from 'vue-meta'






// CKEditor importing
import CKEditor from '@ckeditor/ckeditor5-vue';
        // // CKEditor Translation
        // import '@ckeditor/ckeditor5-build-classic/build/translations/ko';


// Bootstrap importing
import "bootstrap/dist/css/bootstrap.css"
import "bootstrap"
        //Bootstrap Icon https://icons.getbootstrap.com/
        import 'bootstrap-icons/font/bootstrap-icons.css'

// import custom.scss for sass
import './sass/custom.sass'


createApp(App).use(store).use(router).use(CKEditor).use(createMetaManager()).use(vue3GoogleLogin, {
        clientId: '384093093416-n2gqkg3jff53e6vb0rt3ugp7236tcpal.apps.googleusercontent.com'
}).mount('#app')
