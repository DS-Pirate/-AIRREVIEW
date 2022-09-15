import { createApp } from 'vue'
import App from '@/App.vue'
import router from '@/router'
import store from '@/store'






// CKEditor importing
import CKEditor from '@ckeditor/ckeditor5-vue';
        // // CKEditor Translation
        // import '@ckeditor/ckeditor5-build-classic/build/translations/ko';


// Bootstrap importing
import "bootstrap/dist/css/bootstrap.min.css"
// import "bootstrap"
// import "https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
        //Bootstrap Icon https://icons.getbootstrap.com/
        import 'bootstrap-icons/font/bootstrap-icons.css'

// import custom.scss for sass
import './sass/custom.sass'

// // FontAwesome importing
// import { library } from '@fortawesome/fontawesome-svg-core'
// import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
// import { faUserSecret, faLock, faLockOpen, faCircleUser, faBorderAll, faMagnifyingGlass, faHeart, faCircleQuestion, faCommentDots, faStar, faTruck, faGear, faBars, faPhoneFlip, faTicket, faCircleCheck, faX, faHome, faFloppyDisk, faClockRotateLeft, faBell, faEnvelope} from '@fortawesome/free-solid-svg-icons'
// library.add(faUserSecret,faLock, faLockOpen, faCircleUser, faBorderAll, faMagnifyingGlass, faHeart,faCircleQuestion, faCommentDots, faStar, faTruck, faGear, faBars, faPhoneFlip, faTicket, faCircleCheck, faX, faHome, faFloppyDisk, faClockRotateLeft, faBell, faEnvelope)
// .component('font-awesome-icon', FontAwesomeIcon)

createApp(App).use(store).use(router).use(CKEditor).mount('#app')
