<script setup>
import { RouterView } from 'vue-router'
</script>

<template>
  <div class="app-shell">
    <RouterView v-slot="{ Component }">
      <transition name="fade-slide" mode="out-in">
        <component :is="Component" />
      </transition>
    </RouterView>
  </div>
</template>
