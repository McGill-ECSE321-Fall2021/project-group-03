import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/Home'
import MenuBar from '@/components/MenuBar'
import Login from '@/views/Login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
		path: '/login',
		name: 'Login',
		component: Login
	}
  ]
})
