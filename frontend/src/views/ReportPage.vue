<template>
	<Report class="d-flex justify-content-center align-items-center ">
		<div class="report rounded-3 p-4 shadow-sm px-5 w-100 h-75 bg-white">
            <div class="report rounded-3 py-3 shadow-sm d-flex justify-content-center align-items-center"><h2 class="display-6 fw-bold">신고 내역</h2></div>
			<ReportList></ReportList>
		</div>
	</Report>
</template>
<script>
import ReportList from "../components/ReportList.vue";
export default {
	components: { ReportList },
};
</script>
<style lang=""></style>
