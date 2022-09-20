<template>
    <div class="adminpage container d-flex justify-content-center flex-column align-items-center">
        <ul class="adminpage-nav list-group list-group-flush list-group-horizontal gap-5 mb-5">
            <router-link to="/admin/user"> <li class="list-group-item border border-0">사용자 관리</li></router-link>
            <router-link to="/admin/article"><li class="list-group-item border border-0">글 관리</li></router-link>
            <router-link to="/admin/report"><li class="list-group-item border border-0">신고 관리</li></router-link>
            <router-link to="/admin/question"><li class="list-group-item border border-0">Q&A 관리</li></router-link>
        </ul>
        <router-view></router-view>
    </div>
</template>
<script>
export default {
    
}
</script>
<style lang="sass">
    li
        cursor: pointer
    a
        text-decoration: none    
</style>