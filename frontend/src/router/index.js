import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
// import LoginPage from '../views/LoginPage.vue'
const LoginPage = () => import(/* webpackChunkName: "LoginPage" */ '../views/LoginPage.vue');
// import ReadReview from '../views/ReadReview.vue'
const ReadReview = () => import(/* webpackChunkName: "ReadReview" */ '../views/ReadReview.vue');
// import SearchList from '../views/SearchList.vue'
const SearchList = () => import(/* webpackChunkName: "SearchList" */ '../views/SearchList.vue');
// import MyAccountSettings from '../views/MyAccountSettings.vue'
const MyAccountSettings = () => import(/* webpackChunkName: "MyAccountSettings" */ '../views/MyAccountSettings.vue');
// import JoinPage from '../views/JoinPage.vue'
const JoinPage = () => import(/* webpackChunkName: "JoinPage" */ '../views/JoinPage.vue');
// import AccountFind from '../views/AccountFindPage.vue'
const AccountFind = () => import(/* webpackChunkName: "AccountFind" */ '../views/AccountFindPage.vue');
// import Favorite from '../views/FavoritePage.vue'
const Favorite = () => import(/* webpackChunkName: "Favorite" */ '../views/FavoritePage.vue');
// import Saved from '../views/SavedPage.vue'
const Saved = () => import(/* webpackChunkName: "Saved" */ '../views/SavedPage.vue');
// import Mypage from '../views/MychannelPage.vue'
const Mypage = () => import(/* webpackChunkName: "Mypage" */ '../views/MychannelPage.vue');
// import Write from "../views/ArticleWritePage.vue"
const Write = () => import(/* webpackChunkName: "Write" */ "../views/ArticleWritePage.vue");
// import Modify from "../views/ArticleModifyPage.vue"
const Modify = () => import(/* webpackChunkName: "Modify" */ "../views/ArticleModifyPage.vue");
// import PostListPage from "../views/PostListPage.vue"
const PostListPage = () => import(/* webpackChunkName: "PostListPage" */ "../views/PostListPage.vue");
// import ReportPage from "../views/ReportPage.vue"
const ReportPage = () => import(/* webpackChunkName: "ReportPage" */ "../views/ReportPage.vue");
// import RecentPage from "../views/RecentList.vue"
const RecentPage = () => import(/* webpackChunkName: "RecentPage" */ "../views/RecentList.vue");
// import LogOut from "../views/LogoutPage.vue"
const LogOut = () => import(/* webpackChunkName: "LogOut" */ "../views/LogoutPage.vue");
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },

  {
    path:'/login',
    name: 'login',
    component: LoginPage
  },
  {
    path:'/join',
    name: 'join',
    component: JoinPage
  },
  {
    path:'/find',
    name:'AccountFind',
    component: AccountFind
  },
  {
    path: '/fav',
    name: 'favorite',
    component: Favorite

  },
  {
    path: '/save',
    name: 'saved',
    component: Saved
  },
  {
    path: '/mypage',
    name: 'mypage',
    component: Mypage
  },
  {
    path: '/newpost',
    name: 'write',
    component: Write
  },
  {
    path: '/modify',
    name: 'mofify',
    component : Modify
  },
  {
    path: '/read',
    name: 'readReview',
    component: ReadReview,
    props: true
  },

  {
    path: '/search',
    name: 'SearchList',
    component: SearchList
  },
  {
    path: '/setting',
    name: 'MyAccountSettings',
    component: MyAccountSettings
  },
  {
    path: '/list',
    name: 'list',
    component: PostListPage
  },
  {
    path: '/report',
    name: 'report',
    component: ReportPage
  },
  {
    path: '/recent',
    name: 'recent',
    component: RecentPage
  },
  {
    path: '/logout',
    name: 'logout',
    component: LogOut
  }
]



const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
