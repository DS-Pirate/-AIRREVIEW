<template>
	<Postlist class="d-flex justify-content-center align-items-center ">
		<div class="report rounded-3 p-4 shadow-sm px-5 w-100 h-75 bg-white">
            <div class="report rounded-3 py-3 shadow-sm d-flex justify-content-center align-items-center"><h2 class="display-6 fw-bold">글 목록</h2></div>
			<PostList></PostList>
		</div>
	</Postlist>
</template>
<script>
import PostList from "../components/PostList.vue";
export default {
	components: { PostList },
};
</script>
<style lang=""></style>
