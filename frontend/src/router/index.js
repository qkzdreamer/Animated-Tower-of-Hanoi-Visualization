import { createRouter, createWebHistory } from 'vue-router'
import LandingView from '../views/LandingView.vue'
import BigScreenView from '../views/BigScreenView.vue'
import SystemLayout from '../views/SystemLayout.vue'
import SystemHomeView from '../views/SystemHomeView.vue'
import InventoryListView from '../views/InventoryListView.vue'
import InventoryDetailView from '../views/InventoryDetailView.vue'
import SystemSettingsView from '../views/SystemSettingsView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'landing',
      component: LandingView,
    },
    {
      path: '/screen',
      name: 'screen',
      component: BigScreenView,
    },
    {
      path: '/system',
      component: SystemLayout,
      children: [
        {
          path: '',
          name: 'system-home',
          component: SystemHomeView,
        },
        {
          path: 'inventory',
          name: 'inventory',
          component: InventoryListView,
        },
        {
          path: 'inventory/:id',
          name: 'inventory-detail',
          component: InventoryDetailView,
          props: true,
        },
        {
          path: 'settings',
          name: 'settings',
          component: SystemSettingsView,
        },
      ],
    },
  ],
})

export default router
