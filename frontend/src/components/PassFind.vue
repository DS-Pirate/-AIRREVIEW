<template>
	<form class="d-flex flex-column" action="login" method="post">
		<span class="text-center">아이디</span>
		<div class="input-group idsection px-5 my-2">
			<input class="form-control" type="text" name="id" id="id" required placeholder="아이디" />
		</div>

		<span class="text-center" id="pwFindAnswer">Q1. dummyyyyy</span>
		<div class="input-group passwordsection px-5 my-2 w-100">
			<input class="form-control" type="password" name="password" id="password" placeholder="" required />
		</div>
		<div class="px-5 my-4">
			<button type="button" class="btn btn-outline-primary w-100">비밀번호 찾기</button>
		</div>
	</form>
</template>
<script>
export default {};
</script>
<style lang=""></style>
