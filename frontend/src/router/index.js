import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
const LoginPage = () => import(/* webpackChunkName: "LoginPage" */ '../views/LoginPage.vue');
const ReadReview = () => import(/* webpackChunkName: "ReadReview" */ '../views/ReadReview.vue');
const SearchList = () => import(/* webpackChunkName: "SearchList" */ '../views/SearchList.vue');
const MyAccountSettings = () => import(/* webpackChunkName: "MyAccountSettings" */ '../views/MyAccountSettings.vue');
const JoinPage = () => import(/* webpackChunkName: "JoinPage" */ '../views/JoinPage.vue');
const AccountFind = () => import(/* webpackChunkName: "AccountFind" */ '../views/AccountFindPage.vue');
const Favorite = () => import(/* webpackChunkName: "Favorite" */ '../views/FavoritePage.vue');
const Saved = () => import(/* webpackChunkName: "Saved" */ '../views/SavedPage.vue');
const Mypage = () => import(/* webpackChunkName: "Mypage" */ '../views/MychannelPage.vue');
const Write = () => import(/* webpackChunkName: "Write" */ "../views/ArticleWritePage.vue");
const Modify = () => import(/* webpackChunkName: "Modify" */ "../views/ArticleModifyPage.vue");
const PostListPage = () => import(/* webpackChunkName: "PostListPage" */ "../views/PostListPage.vue");
const ReportPage = () => import(/* webpackChunkName: "ReportPage" */ "../views/ReportPage.vue");
const RecentPage = () => import(/* webpackChunkName: "RecentPage" */ "../views/RecentList.vue");
const LogOut = () => import(/* webpackChunkName: "LogOut" */ "../views/LogoutPage.vue");
const Embed = () => import(/* webpackChunkName: "Embed" */ "@/views/EmbedPage.vue")
const AdminPage = () => import(/* webpackChunkName: "AdminPage" */ "@/views/AdminPage.vue");
const AdminUserList = () => import(/* webpackChunkName: "AdminUserList" */ "@/components/AdminUserList.vue");
const AdminArticleList = () => import(/* webpackChunkName: "AdminArticleList" */ "@/components/AdminArticleList.vue");
const AdminReportedArticleList = () => import(/* webpackChunkName: "AdminReportedArticleList" */ "@/components/AdminReportedArticleList.vue");
const AdminQuestionList = () => import(/* webpackChunkName: "AdminQuestionList" */ "@/components/AdminQuestionList.vue");
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
  },
  {
    path: '/embed',
    name: 'embed',
    component: Embed
  },
  {
    path: '/admin/',
    name: 'AdminPage',
    component: AdminPage,
    children: [
      {
        path: '/',
        name: 'AdminPage',
        component: AdminPage,
      },
      {
        path: 'user',
        name: 'usermanage',
        component: AdminUserList
      },
      {
        path: 'article',
        name: 'articlemanage',
        component: AdminArticleList
      },
      {
        path: 'report',
        name: 'reportmanage',
        component: AdminReportedArticleList
      },
      {
        path: 'question',
        name: 'questionmanage',
        component: AdminQuestionList
      }
    ]
  }
]



const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
