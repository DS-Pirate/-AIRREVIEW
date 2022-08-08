<template>
	<nav class="navbar sticky-top navbar-light bg-white border-bottom">
		<div class="container-fluid">
			<div class="navv-leftside align-item-center" justify-content-space-between>
				<button class="btn btn-link youhavetobeonsupertop" type="button" data-bs-toggle="offcanvas"
					data-bs-target="#menup-ofcanvas" aria-controls="menup-ofcanvas">
					<font-awesome-icon icon="fa-solid fa-bars" />
				</button>
				<span class="navbar-brand btn btn-link fw-bold navbtitle">Air 리뷰</span>
				<div class="navmenubarabsolute"></div>
			</div>
			<form class="d-flex navv-middle">
				<input class="form-control me-8" type="search" placeholder="" aria-label="Search" />
				<button class="btn btn-link makingoverlap" type="submit">
					<font-awesome-icon icon="fa-solid fa-magnifying-glass" />
				</button>
			</form>
			<div class="navv-right">
				<div class="dropdown dropstart navv-right">
					<button class="btn btn-link" type="button" data-bs-toggle="dropdown" aria-expanded="false">
						<font-awesome-icon icon="fa-solid fa-circle-user fa-2x" />
					</button>
					<ul class="dropdown-menu">
						<li class="nav-item d-flex m-0">
							<a href="#" class="nav-link active d-flex flex-row justify-content-left align-items-center w-100 p-2"
								aria-current="page">
								<img src="@/assets/pngwing.com.png" class="bi me-2" width="25" height="25" />
								<span>subsdummy1</span>
							</a>
						</li><hr>
						<router-link to="/newpost" class="dropdown-item">새 글</router-link>
						<router-link to="/login" class="dropdown-item">로그인</router-link>
						<router-link to="/logout" class="dropdown-item">로그아웃</router-link>
						<router-link to="/join" class="dropdown-item">회원가입</router-link>
						<div class="d-flex flex-row">
							<li><a class="dropdown-item" href="#">다크 모드</a></li>
							<div class="form-check form-switch align-self-center">
  <input class="form-check-input" type="checkbox" role="switch" id="flexSwitchCheckDefault">
  <label class="form-check-label" for="flexSwitchCheckDefault"></label>
							</div>
						</div>
						<router-link to="/service" class="dropdown-item">고객센터</router-link>
						<router-link to="/setting" class="dropdown-item">설정</router-link>
					</ul>
				</div>
				
				<div class="offcanvas offcanvas-start d-flex" data-bs-scroll="true" data-bs-backdrop="false" tabindex="-1"
					id="menup-ofcanvas" aria-labelledby="menup-ofcanvasLabel">
					<div class="offcanvas-header">
						<h5 class="offcanvas-title" id="menup-ofcanvas"></h5>
						<span class="navbar-brand btn btn-link fw-bold navbtitle">Air 리뷰</span>
						<button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
					</div>
					<div class="offcanvas-body">
						<ul class="list-group">
							<li class="list-group-item">
								<font-awesome-icon icon="fa-solid fa-home" />
							</li>
							<li class="list-group-item">
								<font-awesome-icon icon="fa-solid fa-heart" />
							</li>
							<li class="list-group-item">
								<font-awesome-icon icon="fa-solid fa-floppy-disk" />
							</li>
							<li class="list-group-item">
								<font-awesome-icon icon="fa-solid fa-clock-rotate-left" />
							</li>
							<li class="list-group-item">
								<font-awesome-icon icon="fa-solid fa-bell" />
							</li>
						</ul>
						<hr>
						<div>
							<SubscribeList v-for="i in 3" :key="i"></SubscribeList>
						</div>
					</div>

					<div class="offcanvas-footer justify-content-center flex-column d-flex p-3">
						<span>Copyright. DS.Pirate</span>
						<span>Team Information</span>
					</div>

				</div>
			</div>
		</div>
	</nav>
</template>
<script>

import SubscribeList from '@/components/SubscribeList.vue';
export default {
	component: {
		SubscribeList
	},
	components: { SubscribeList }
};
</script>
<style lang=""></style>
