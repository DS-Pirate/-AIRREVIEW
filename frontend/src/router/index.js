import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginPage from '../views/LoginPage.vue'
import ReadReview from '../views/ReadReview.vue'
import SearchList from '../views/SearchList.vue'
import MyAccountSettings from '../views/MyAccountSettings.vue'
import JoinPage from '../views/JoinPage.vue'
import AccountFind from '../views/AccountFindPage.vue'
import Favorite from '../views/FavoritePage.vue'
import Saved from '../views/SavedPage.vue'
import Mypage from '../views/MychannelPage.vue'
import Write from "../views/ArticleWritePage.vue"
import PostListPage from "../views/PostListPage.vue"
import ReportPage from "../views/ReportPage.vue"
import RecentPage from "../views/RecentList.vue"
import LogOut from "../views/LogoutPage.vue"
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
  // {
  //   path: '/about',
  //   name: 'about',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  // }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
