<template>
	<div class="col maincards">
		<router-link to="/read">
			<div class="card mb-3" style="max-width: 720px">
				<div class="row g-0">
					<div class="col-md-4">
						<img src="@/assets/dummy.webp" class="img-fluid" alt="..." />
					</div>
					<div class="col-md-8">
						<div class="card-body">
							<h5 class="card-title">글 제목 {{ cardinfo }}</h5>
							<p class="card-text">이곳에 글의 내용이 들어가게됩니다 글자는 최대 40자 정도를 생각하고 있습니다</p>
							<p class="card-text d-flex align-items-center justify-content-between">
								<span>
									<img class="rounded-4" width="30" height="30" src="@/assets/dummy.webp" />
									<span class="mx-1">username1</span>
								</span>
								<small class="text-muted">3분 전</small>
							</p>
						</div>
					</div>
				</div>
			</div>
		</router-link>
	</div>
</template>

<script>
export default {
	props: ["cardinfo"],
};
</script>
