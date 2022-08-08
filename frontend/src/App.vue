<template>
	<Nav></Nav>
	<div class="content-area">
		<SubNavigationBar/>
		<router-view class="mainviewpage" />
	</div>
	<div class="absolutebar"></div>
</template>
<script>
// @ is an alias to /src
import Nav from '@/components/NavigationBar.vue'
import SubNavigationBar from './components/SubNavigationBar.vue'


export default {
  name: 'TopOfPage',
  components: {
    Nav,
    SubNavigationBar,

}
}
</script>
