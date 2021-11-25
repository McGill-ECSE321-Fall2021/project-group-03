import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/Home'
import Login from '@/views/Login'
import Browse from '@/views/Browse'
import Account from '@/views/Account'
import TitleInformation from '@/views/TitleInformation'

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
	},
	{
		path: '/browse',
		name: 'Browse',
		component: Browse
	},
	{
		path: '/account',
		name: 'Account',
		component: Account
	},
	{
		path: '/browse/:name',
		name: TitleInformation,
		component: TitleInformation
	}
  ]
})
