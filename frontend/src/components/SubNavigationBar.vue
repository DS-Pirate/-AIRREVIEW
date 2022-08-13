<template>
	<subNav>
		<div class="navbar container-fluid d-flex flex-column justify-content-center align-items-start sticky-top">
			<div class="navlogo">
				<router-link to="/"><h1 class="mb-5 fw-bold my-2">AIR 
					<br>REVIEW</h1></router-link>
			</div>
			<div class="navbody my-2">
				<ul class="list-group list-group-flush">
					<router-link to="/"><li class="list-group-item my-1">HOME</li></router-link>
					<router-link to="/fav"><li class="list-group-item my-1">FAVORITE</li></router-link>
					<router-link to="/save"><li class="list-group-item my-1">SAVED</li></router-link>
					<router-link to="/recent"><li class="list-group-item my-1">RECENTLY</li></router-link>
					<li class="list-group-item my-1" data-bs-toggle="modal" data-bs-target="#question">1:1 Q&A</li>
					<li class="list-group-item my-1" data-bs-toggle="offcanvas" data-bs-target="#subscribe" aria-controls="offcanvasWithBothOptions">SUBSCRIBE</li>
				</ul>
			</div>
			<div class="navfooter mt-5">
				<h6>Proeject by Team Pirate</h6>
				<h6><a href="https://github.com/DS-Pirate" class="bi bi-github">&nbsp;Project repository</a></h6>
				<h6><i class="bi bi-funnel-fill"></i>&nbsp;Busan, Korea</h6>
			</div>
		</div>
		<SubscribeOffcanvas />
	</subNav>
</template>
<script>
import SubscribeOffcanvas from './SubscribeOffcanvas.vue';
export default { components: { SubscribeOffcanvas } };
</script>
<style scoped>
	.navfooter{
		color: rgba(200, 200, 200);
		
	}
	.navfooter a{
		text-decoration: none;
		color: rgba(200, 200, 200, 0.9);
		transition: all ease-in-out 0.3s;
	}
	h6{
		font-size: 0.7rem;
		transition: all ease-in-out 0.3s;
	}

	.navfooter a:hover,
	h6:hover{
		color: black;
	}
</style>
