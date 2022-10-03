<template>
	<div class="d-flex justify-content-center align-items-center joinform">
		<div class="join ">
			<div class="joinlogo text-center"><img class="img-fluid" src="@/assets/airreviewlogo.png" alt="" srcset=""></div>
			<SiteJoin></SiteJoin>
		</div>
	</div>
</template>
<script>
import SiteJoin from "../components/SiteJoin.vue";
import {reactive} from "vue";
export default {
	components: { SiteJoin },

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
