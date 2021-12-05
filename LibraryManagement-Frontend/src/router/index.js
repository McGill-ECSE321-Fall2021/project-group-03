import Vue from 'vue'
import Router from 'vue-router'
import Room from '@/views/Room'
import TitleInformation from '@/views/TitleInformation'
import Management from '@/views/Management'
import RoomInformation from '@/views/RoomInformation'
import Tools from '@/views/Tools.vue'
import Reservations from '@/views/Reservations'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: require('../views/Home.vue').default
    },
    {
		path: '/login',
		name: 'Login',
		component: require('../views/Login.vue').default
	},
	{
		path: '/browse',
		name: 'Browse',
		component: require('../views/Browse.vue').default
	},
	{
		path: '/account',
		name: 'Account',
		component: require('../views/Account.vue').default
	},
	{
		path: '/rooms/',
		name: Room,
		component: require('../views/Room.vue').default
	},
	{
		path: '/browse/room/:id',
		name: RoomInformation,
		component: require('../views/RoomInformation.vue').default
	},
	{
		path: '/browse/title/:name',
		name: TitleInformation,
		component: require('../views/TitleInformation.vue').default
	},
	{
		path: '/tools',
		name: Tools,
		component: require('../views/Tools.vue').default
	},
	{
		path: '/management',
		name: Management,
		component: require('../views/Management.vue').default
	},
	{
		path: '/reservations',
		name: Reservations,
		component: require('../views/Reservations.vue').default
	}
  ]
})
