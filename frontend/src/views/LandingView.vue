<script setup>
import { ref } from 'vue'
import { RouterLink } from 'vue-router'

const activeCard = ref('')

const handleSelect = (card) => {
  activeCard.value = card
}
</script>

<template>
  <div class="landing">
    <header class="landing-header">
      <div>
        <p class="landing-kicker">智慧仓储演示雏形</p>
        <h1>库区大屏 + 系统操作台</h1>
        <p class="landing-subtitle">
          仅包含图片与点击动效，数据由本地 Mock 提供，已预留后端接口对接位置。
        </p>
      </div>
      <div class="landing-tag">
        <span>离线演示</span>
      </div>
    </header>

    <div class="landing-grid">
      <RouterLink
        to="/screen"
        class="landing-card"
        :class="{ active: activeCard === 'screen' }"
        @click="handleSelect('screen')"
      >
        <img src="@/assets/images/warehouse.svg" alt="大屏" />
        <h2>库的大屏</h2>
        <p>全屏展示 · 自动轮播 · 轻交互</p>
        <div class="landing-action">进入大屏</div>
      </RouterLink>

      <RouterLink
        to="/system"
        class="landing-card"
        :class="{ active: activeCard === 'system' }"
        @click="handleSelect('system')"
      >
        <img src="@/assets/images/dashboard.svg" alt="系统" />
        <h2>系统界面</h2>
        <p>登录后操作台 · 列表/详情/统计</p>
        <div class="landing-action">进入系统</div>
      </RouterLink>
    </div>

    <section class="landing-note">
      <h3>对接说明</h3>
      <p>
        前端已预留 API 模块，后端提供 Spring Boot Mock 接口。后续替换为真实 API 时，只需修改
        <code>frontend/src/api</code> 的请求实现与字段映射。
      </p>
    </section>
  </div>
</template>

<style scoped>
.landing {
  min-height: 100vh;
  padding: 48px clamp(24px, 4vw, 64px);
  display: flex;
  flex-direction: column;
  gap: 32px;
  color: #e9eef7;
  background: radial-gradient(circle at top, #1b2a4a, #0b1020 65%);
}

.landing-header {
  display: flex;
  justify-content: space-between;
  gap: 24px;
  align-items: center;
}

.landing-kicker {
  text-transform: uppercase;
  letter-spacing: 0.18em;
  font-size: 12px;
  color: #9bb1ff;
  margin-bottom: 8px;
}

.landing-subtitle {
  max-width: 520px;
  color: #c7d2f5;
  margin-top: 12px;
}

.landing-tag {
  background: rgba(80, 126, 255, 0.2);
  border: 1px solid rgba(80, 126, 255, 0.5);
  padding: 12px 20px;
  border-radius: 999px;
  font-size: 13px;
}

.landing-grid {
  display: grid;
  gap: 24px;
  grid-template-columns: repeat(auto-fit, minmax(260px, 1fr));
}

.landing-card {
  background: rgba(20, 30, 55, 0.7);
  border: 1px solid rgba(120, 150, 255, 0.2);
  border-radius: 20px;
  padding: 28px;
  display: flex;
  flex-direction: column;
  gap: 12px;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.landing-card img {
  width: 56px;
  height: 56px;
}

.landing-card.active {
  transform: translateY(-4px) scale(1.02);
  box-shadow: 0 18px 40px rgba(36, 86, 255, 0.3);
}

.landing-card:hover {
  transform: translateY(-2px) scale(1.01);
}

.landing-action {
  margin-top: auto;
  background: rgba(80, 126, 255, 0.15);
  color: #d9e3ff;
  padding: 8px 16px;
  border-radius: 999px;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  font-size: 13px;
}

.landing-note {
  border-radius: 16px;
  padding: 20px 24px;
  background: rgba(255, 255, 255, 0.06);
}

.landing-note code {
  background: rgba(255, 255, 255, 0.12);
  padding: 2px 6px;
  border-radius: 6px;
}
</style>
