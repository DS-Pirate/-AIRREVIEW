<template>
	<subNav>
		<div class="navbar container-fluid d-flex flex-column justify-content-center align-items-start sticky-top">
			<div class="navlogo">
				<router-link to="/"><h1 class="mb-5 fw-bold my-3">AIR 
					<br>REVIEW</h1></router-link>
			</div>
			<div class="navbody my-4">
				<ul class="list-group list-group-flush">
					<router-link to="/"><li class="list-group-item my-1">HOME</li></router-link>
					<router-link to="/fav"><li class="list-group-item my-1">FAVORITE</li></router-link>
					<router-link to="/save"><li class="list-group-item my-1">SAVED</li></router-link>
					<router-link to="/recent"><li class="list-group-item my-1">RECENTLY</li></router-link>
					<li class="list-group-item my-1" data-bs-toggle="modal" data-bs-target="#question">1:1 Q&A</li>
					<li class="list-group-item my-1">SUBSCRIBE</li>
				</ul>
			</div>
		</div>
	</subNav>
</template>
<script>
export default {};
</script>
<style>
</style>
