<template>
	<form class="d-flex flex-column" action="login" method="post">
		<span class="answer text-center" id="answerForRecovery1">이메일</span>
		<div class="input-group idsection px-5 my-2">
			<input class="form-control" type="text" name="id" id="id" required placeholder="이메일" />
		</div>
		<span class="answer text-center" id="answerForRecovery1">Q1. dummmy</span>
		<div class="input-group idsection px-5 my-2">
			<input class="form-control" type="text" name="id" id="id" required placeholder="" />
		</div>
		<span class="answer text-center" id="answerForRecovery2">Q2. dummy</span>
		<div class="input-group passwordsection px-5 my-2 w-100">
			<input class="form-control" type="password" name="password" id="password" placeholder="" required />
		</div>

		<div class="px-5 my-4">
			<button type="button" class="btn btn-outline-primary w-100">아이디 찾기</button>
		</div>
	</form>
</template>
<script>
export default {};
</script>
<style lang=""></style>
