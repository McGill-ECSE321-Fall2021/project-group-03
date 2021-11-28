import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/Home'
import SignIn from '@/views/SignIn'
import Browse from '@/views/Browse'
import Account from '@/views/Account'
import Room from '@/views/Room'
import TitleInformation from '@/views/TitleInformation'
import Management from '@/views/Management'
import RoomInformation from '@/views/RoomInformation'
import Inventory from '@/views/Inventory'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
		path: '/signin',
		name: 'SignIn',
		component: SignIn
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
		path: '/rooms/',
		name: Room,
		component: Room
	},
	{
		path: '/browse/room/:name',
		name: RoomInformation,
		component: RoomInformation
	},
	{
		path: '/browse/title/:name',
		name: TitleInformation,
		component: TitleInformation
	},
	{
		path: '/inventory',
		name: Inventory,
		component: Inventory
	},
	{
		path: '/management',
		name: Management,
		component: Management
	}
  ]
})
