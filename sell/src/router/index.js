import Vue from 'vue'
import Router from 'vue-router'

import good from '../components/goods/good'
import rating from '../components/ratings/rating'
import seller from '../components/sellers/seller'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/good',
      name: 'good',
      component: good
    },
    {
      path: '/rating',
      name: 'rating',
      component: rating
    },
    {
      path: '/seller',
      name: 'seller',
      component: seller
    }
  ],
  mode: 'history'
})
