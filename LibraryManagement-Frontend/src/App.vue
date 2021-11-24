<template>
  <div id="app">
    <router-view>
    </router-view>
  </div>
</template>

<script>
import MenuBar from './components/MenuBar.vue'
import Home from './views/Home.vue'

export default {
  name: 'App',
  components: {
    MenuBar,
    Home
  }
}
</script>
<style>
</style>
