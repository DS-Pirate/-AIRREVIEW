<template>
	<subNav>
		<ul class="list-group">
			<router-link to="/">
				<li class="list-group-item">
					<font-awesome-icon icon="fa-solid fa-home" />
				</li>
			</router-link>
			<router-link to="/fav">
				<li class="list-group-item">
					<font-awesome-icon icon="fa-solid fa-heart" />
				</li>
			</router-link>
			<router-link to="/save">
				<li class="list-group-item">
					<font-awesome-icon icon="fa-solid fa-floppy-disk" />
				</li>
			</router-link>
			<router-link to="/recent">
				<li class="list-group-item">
					<font-awesome-icon icon="fa-solid fa-clock-rotate-left" />
				</li>
			</router-link>
		</ul>

	</subNav>
</template>
<script>
export default {

};
</script>
<style>
</style>
