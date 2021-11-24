import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/Home'
import MenuBar from '@/components/MenuBar'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
		path: '/menuBar',
		name: 'LibraryManagement',
		component: MenuBar
	}
  ]
})
