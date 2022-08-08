<template>
	<Service class="d-flex justify-content-center align-items-center ">
		<div class="service rounded-3 p-4 shadow-sm px-5 w-100 h-75 bg-white">
            <div class="service rounded-3 py-3 shadow-sm d-flex justify-content-center align-items-center"><h2 class="display-6 fw-bold">고객센터</h2></div>
			<SiteService></SiteService>
		</div>
	</Service>
</template>
<script>
import SiteService from "../components/SiteService.vue";
import { reactive} from "vue";
export default {
	components: { SiteService },

	setup() {
        let state = reactive({
            form: "true"
        })
        function changeform(a){
            if (a =="true"){
                state.form = true
                
            }else if(a=='false'){
                state.form = false
            }
            console.log(state.form);
        }
		return {state, changeform};
	},
};
</script>
<style lang=""></style>
